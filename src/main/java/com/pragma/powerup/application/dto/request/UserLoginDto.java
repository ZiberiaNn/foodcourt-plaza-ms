package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    @Schema(example = "example@example.com", required = true)
    private String email;
    @Schema(example = "example", required = true)
    private String password;
}