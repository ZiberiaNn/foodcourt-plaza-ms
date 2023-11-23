package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.DishModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishQtyResponseDto {
    private DishModel dish;
    private Integer dishQuantity;
}
