package com.nht.activitytrackerserver.security;

import com.nht.activitytrackerserver.repository.UserRepository;
import com.nht.activitytrackerserver.security.authEntryPoints.CustomAuthenticationEntryPointResolver;
import com.nht.activitytrackerserver.security.converter.PrivateKeyConverter;
import com.nht.activitytrackerserver.security.converter.PublicKeyConverter;
import com.nht.activitytrackerserver.service.CustomUserDetailsService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private Resource keyStr; //RSAPublicKey

    @Value("${jwt.private.key}")
    private Resource privStr; // RSAPrivateKey

    private final PublicKeyConverter publicKeyConverter = new PublicKeyConverter();
    private final PrivateKeyConverter privateKeyConverter = new PrivateKeyConverter();

    private final AuthenticationConfiguration authConfiguration;

    @Autowired
    private CustomAuthenticationEntryPointResolver entryPointResolver;


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/auth/register").hasAuthority("SCOPE_ADMIN")
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/api/auth/**", "/h2-console/**"))
                .headers(configure -> configure.frameOptions( // TODO DELETE THIS ONE - INSECURE
                        frameOptionsConfig -> frameOptionsConfig.disable()
                ))
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptions) -> exceptions
//                        .authenticationEntryPoint(new LoginRegisterEntryPoint())
//                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .authenticationEntryPoint((request, response, authException) ->
                                entryPointResolver.resolveEntryPoint(request)
                                        .commence(request, response, authException))
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                );

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKeyConverter.convert(keyStr)).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(publicKeyConverter.convert(keyStr)).privateKey(privateKeyConverter.convert(privStr)).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }

//    @Bean
//    @Transactional
//    public UserDetailsService userDetailsService(UserRepository userRepo) {
//        return username -> {
//            log.info("Looking for a user - UserDetails");
//            Optional<AppUser> user = userRepo.findByUsername(username);
//            if (user.isPresent()) {
//                return user.get();
//            }
//            throw new UsernameNotFoundException("User '" + username + "' not found");
//        };
//    }
}
