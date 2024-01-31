package com.nht.activitytrackerserver.service;

import com.nht.activitytrackerserver.entity.AppUser;
import com.nht.activitytrackerserver.model.auth.request.LoginRequestDto;
import com.nht.activitytrackerserver.model.auth.request.RegisterRequestDto;
import com.nht.activitytrackerserver.model.auth.response.LoginResponseDto;
import org.springframework.security.core.Authentication;

public interface IAuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    AppUser register(RegisterRequestDto registerRequestDto);
}
