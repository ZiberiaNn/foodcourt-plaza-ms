package com.pragma.powerup.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantGetResponseDto {
    @Schema(example = "0")
    private Long id;
    @Schema(example = "Example Name")
    private String name;
    @Schema(example = "www.example.com/logo.png")
    private String urlLogo;
}
