package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserLoginDto;
import com.pragma.powerup.application.dto.response.AuthTokenResponseDto;
import com.pragma.powerup.application.handler.IAuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthRestController {
    private final IAuthHandler authHandler;
    @PostMapping("/auth")
    public ResponseEntity<AuthTokenResponseDto> generateToken(@RequestBody UserLoginDto userLoginDto) throws AuthenticationException {
        return ResponseEntity.ok(authHandler.authenticateUser(userLoginDto));
    }
}
