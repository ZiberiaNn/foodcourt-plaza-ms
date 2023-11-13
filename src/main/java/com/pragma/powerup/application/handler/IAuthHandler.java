package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserLoginDto;
import com.pragma.powerup.application.dto.response.AuthTokenResponseDto;

public interface IAuthHandler {
    AuthTokenResponseDto authenticateUser(UserLoginDto userLoginDto);
}
