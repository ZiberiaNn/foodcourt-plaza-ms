package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.OrderModel;

import java.util.List;

public interface IOrderPersistencePort {
    OrderModel saveOrder(OrderModel orderModel);
    OrderModel getOrderById(Long id);
    List<OrderModel> getAllOrders();
}
