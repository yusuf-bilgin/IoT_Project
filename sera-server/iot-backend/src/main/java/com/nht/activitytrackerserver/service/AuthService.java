package com.nht.activitytrackerserver.service;

import com.nht.activitytrackerserver.constants.Role;
import com.nht.activitytrackerserver.entity.AppUser;
import com.nht.activitytrackerserver.entity.UserRole;
import com.nht.activitytrackerserver.model.auth.request.LoginRequestDto;
import com.nht.activitytrackerserver.model.auth.request.RegisterRequestDto;
import com.nht.activitytrackerserver.model.auth.response.LoginResponseDto;
import com.nht.activitytrackerserver.repository.UserRepository;
import com.nht.activitytrackerserver.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService implements IAuthService{

    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, UserRoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtEncoder encoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        Instant now = Instant.now();
        long expiry = 36000L;

        AppUser user = (AppUser) authentication.getPrincipal();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        log.info("SCOPE of the user: {}", scope);
        log.info("Authentication: {}", user);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("username", user.getUsername())
                .build();

        return LoginResponseDto.builder()
                .jwt(this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue())
                .build();
    }

    @Override
    @Transactional
    public AppUser register(RegisterRequestDto registerRequestDto) {

        var userExists = userRepository.findByUsername(registerRequestDto.getUsername());

        if (userExists.isPresent()) {
            throw new BadCredentialsException("Username already exists");
        }

        UserRole userRole = roleRepository.findUserRoleByRole(Role.USER).orElse(null);
//        Set<UserRole> roles = new HashSet<>();
//        roles.add(userRole);
        if (userRole == null) throw new RuntimeException("Role could not found");

        AppUser user = AppUser.builder()
                .username(registerRequestDto.getUsername())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .email(registerRequestDto.getEmail())
                .name(registerRequestDto.getName())
                .surname(registerRequestDto.getSurname())
                .build();

        user.getUserRoles().add(userRole);
        //userRole.getUsers().add(user);

        return userRepository.save(user);
    }

}
