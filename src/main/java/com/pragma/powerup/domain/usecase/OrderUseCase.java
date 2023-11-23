package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    @Override
    public OrderModel saveModel(OrderModel orderModel) {
        return orderPersistencePort.saveOrder(orderModel);
    }
    @Override
    public OrderModel getOrderById(Long id) {
        return orderPersistencePort.getOrderById(id);
    }
    @Override
    public List<OrderModel> getAllOrders() {
        return orderPersistencePort.getAllOrders();
    }
}