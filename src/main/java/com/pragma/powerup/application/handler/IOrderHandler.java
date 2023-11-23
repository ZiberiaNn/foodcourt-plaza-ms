package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;

import java.util.List;

public interface IOrderHandler {
    OrderResponseDto saveOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto getOrderById(Long id);
    List<OrderResponseDto> getAllOrders();
}
