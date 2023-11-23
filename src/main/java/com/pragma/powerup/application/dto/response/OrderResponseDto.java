package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private RestaurantGetResponseDto restaurant;
    private String status;
    private List<OrderDishQtyResponseDto> dishQtyList;
}
