package com.pragma.powerup.domain.model;

import com.pragma.powerup.domain.model.enums.StatusEnum;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishQtyEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderModel {
    private Long id;
    private Integer clientIdentityNumber;
    private RestaurantEmployeeModel assignedEmployee;
    private RestaurantModel restaurant;
    private StatusEnum status;
    private List<OrderDishQtyEntity> dishQtyList;
}
