package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.request.PinRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.domain.model.enums.StatusEnum;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderHandler {
    OrderResponseDto saveOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto getOrderById(Long id);
    List<OrderResponseDto> getAllOrders();
    Page<OrderResponseDto> getOrdersByStatusAndIfEmployeeBelongsToOrder(StatusEnum status, int page, int size);

    OrderResponseDto updateOrderAssignedEmployeeAndStatusToEnPreparacion(Long existingOrderId);
    OrderResponseDto updateOrderStatusToDoneAndSendSms(Long existingOrderId, String authToken);

    OrderResponseDto updateOrderStatusToDelivered(Long existingOrderId, PinRequestDto pin, String authToken);
}
