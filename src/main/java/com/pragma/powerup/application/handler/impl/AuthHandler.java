package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserLoginDto;
import com.pragma.powerup.application.dto.response.AuthTokenResponseDto;
import com.pragma.powerup.application.handler.IAuthHandler;
import com.pragma.powerup.infrastructure.security.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthHandler implements IAuthHandler {
    private final AuthenticationManager authenticationManager;
    private final TokenUtils jwtTokenUtil;

    @Override
    public AuthTokenResponseDto authenticateUser(UserLoginDto userLoginDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return new AuthTokenResponseDto(token);
    }
}
