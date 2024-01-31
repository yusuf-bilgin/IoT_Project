package com.nht.activitytrackerserver.security.authEntryPoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nht.activitytrackerserver.model.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
public final class LoginRegisterEntryPoint implements AuthenticationEntryPoint {
    private String realmName;
    public  LoginRegisterEntryPoint() {

    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        Map<String, String> parameters = new LinkedHashMap<>();
        if (realmName != null) {
            parameters.put("realm", realmName);
        }

        if (authException instanceof BadCredentialsException) {
            String errorMessage = authException.getMessage();
            parameters.put("error", BadCredentialsException.class.getSimpleName());
            parameters.put("message", errorMessage);
            status = HttpStatus.BAD_REQUEST;
        }

        response.setStatus(status.value());
        response.setContentType("application/json;charset=UTF-8");

        ErrorResponseDto errorResponse = new ErrorResponseDto(status.value(), parameters.get("error"), authException.getMessage());

        try (PrintWriter writer = response.getWriter()) {
            // Convert the ErrorResponse to JSON and write it to the response body
            writer.write(new ObjectMapper().writeValueAsString(errorResponse));
        }

    }
}
