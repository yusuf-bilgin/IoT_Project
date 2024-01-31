package com.nht.activitytrackerserver.service;

import com.nht.activitytrackerserver.entity.AppUser;
import com.nht.activitytrackerserver.repository.UserRepository;
import com.nht.activitytrackerserver.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//@Service --->  // Created with @Bean in SecurityConfig.java
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            AppUser user = optionalUser.get();
            // Ensure roles are fetched along with the user
//            int roleCount = user.getUserRoles().size(); // This triggers the fetch of roles
//            log.info("Custom User Details : {}", user);
//            log.info("Role count: {}", roleCount);

            return user;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
