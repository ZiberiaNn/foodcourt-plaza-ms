package com.pragma.powerup.domain.model;

import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishQtyKey;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDishQtyModel {
    private OrderDishQtyKey id;
    private OrderModel order;
    private DishModel dish;
    private Integer dishQuantity;
}
