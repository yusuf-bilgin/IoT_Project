package com.nht.activitytrackerserver.controller;

import com.nht.activitytrackerserver.entity.AppUser;
import com.nht.activitytrackerserver.model.auth.request.LoginRequestDto;
import com.nht.activitytrackerserver.model.auth.request.RegisterRequestDto;
import com.nht.activitytrackerserver.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/token")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity register(@Validated @RequestBody RegisterRequestDto registerRequestDto) {
        AppUser savedUser = authService.register(registerRequestDto);
        return ResponseEntity.ok(savedUser);
    }
}
