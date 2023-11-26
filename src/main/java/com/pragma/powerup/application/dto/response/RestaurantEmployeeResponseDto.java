package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantEmployeeResponseDto {
    @Schema(example = "example@example.com")
    private String email;
    private RestaurantEntity restaurant;
}
