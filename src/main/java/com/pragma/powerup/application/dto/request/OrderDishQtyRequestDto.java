package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishQtyRequestDto {
    private OrderDishRequestDto dish;
    @Schema(example = "5")
    private Integer dishQuantity;
}
