package com.nht.activitytrackerserver.model.auth.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String jwt;
}
