package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequestDto {
    @Schema(example = "Example Name")
    private String name;
    @Schema(example = "987654321")
    private String nit;
    @Schema(example = "Example Address")
    private String address;
    @Schema(example = "+573132222222")
    private String phone;
    @Schema(example = "www.example.com/logo.png")
    private String urlLogo;
    @Schema(example = "123456789")
    private Integer ownerUserIdentityNumber;
}