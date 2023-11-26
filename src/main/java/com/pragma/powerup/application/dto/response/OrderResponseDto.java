package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Integer clientIdentityNumber;
    private RestaurantEmployeeResponseDto assignedEmployee;
    private RestaurantGetResponseDto restaurant;
    private StatusEnum status;
    private List<OrderDishQtyResponseDto> dishQtyList;
}
