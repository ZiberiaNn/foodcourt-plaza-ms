package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantEmployeeRequestDto {
    @Schema(example = "employee@employee.com")
    private String email;
    @Schema(example = "12")
    private Long restaurantId;
}
