package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.IOrderRequestMapper;
import com.pragma.powerup.application.mapper.IOrderResponseMapper;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;

    @Override
    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        OrderModel orderModel = orderRequestMapper.toModel(orderRequestDto);
        return orderResponseMapper.toResponse(orderServicePort.createOrder(orderModel, loggedUser.getUsername()));
    }
    @Override
    public OrderResponseDto getOrderById(Long id) {
        return orderResponseMapper.toResponse(orderServicePort.getOrderById(id));
    }
    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderResponseMapper.toResponseList(orderServicePort.getAllOrders());
    }
    @Override
    public Page<OrderResponseDto> getOrdersByStatusAndIfEmployeeBelongsToOrder(StatusEnum status, int page, int size) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return orderServicePort.getOrdersByStatusAndIfEmployeeBelongsToOrder(status.getName(), pageable, loggedUser.getUsername()).map(orderResponseMapper::toResponse);
    }
    @Override
    public OrderResponseDto updateOrderAssignedEmployeeAndStatusToEnPreparacion(Long existingOrderId) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderResponseMapper.toResponse(orderServicePort.updateOrderAssignedEmployeeAndStatusToEnPreparacion(existingOrderId, loggedUser.getUsername()));
    }
}
