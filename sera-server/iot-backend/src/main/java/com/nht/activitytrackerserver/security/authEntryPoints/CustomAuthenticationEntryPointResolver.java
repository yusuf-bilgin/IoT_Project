package com.nht.activitytrackerserver.security.authEntryPoints;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPointResolver implements AuthenticationEntryPointResolver {

    @Override
    public AuthenticationEntryPoint resolveEntryPoint(HttpServletRequest request) {
        // Your logic to determine the appropriate entry point based on the request
        if (request.getRequestURI().startsWith("/api/auth/")) {
            return new LoginRegisterEntryPoint();
        } else {
            return new BearerTokenAuthenticationEntryPoint();
        }
    }

}

interface AuthenticationEntryPointResolver {
    AuthenticationEntryPoint resolveEntryPoint(HttpServletRequest request);
}
