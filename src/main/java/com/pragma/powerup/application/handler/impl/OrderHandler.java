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
        OrderModel orderModel = orderRequestMapper.toModel(orderRequestDto);
        return orderResponseMapper.toResponse(orderServicePort.createOrder(orderModel));
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
    public Page<OrderResponseDto> getOrdersByStatus(StatusEnum status, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return orderServicePort.getOrdersByStatus(status.getName(), pageable).map(orderResponseMapper::toResponse);
    }
}
