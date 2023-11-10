package com.pragma.powerup.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class RestaurantResponseDto {
    @Schema(example = "0")
    private Long id;
    @Schema(example = "Example Name")
    private String name;
    @Schema(example = "978654321")
    private String nit;
    @Schema(example = "Example Address")
    private String address;
    @Schema(example = "+573132222222")
    private String phone;
    @Schema(example = "www.example.com/logo.png")
    private String urlLogo;
    @Schema(example = "123456879")
    private Integer ownerUserIdentityNumber;
}
