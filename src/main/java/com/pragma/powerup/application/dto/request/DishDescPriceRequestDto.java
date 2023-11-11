package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DishDescPriceRequestDto {
    @Schema(example = "8500")
    private Integer price;
    @Schema(example = "New Example Description")
    private String description;
}
