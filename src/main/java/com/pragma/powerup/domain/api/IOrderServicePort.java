package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.OrderModel;

import java.util.List;

public interface IOrderServicePort {
    OrderModel createOrder(OrderModel orderModel);
    OrderModel getOrderById(Long id);
    List<OrderModel> getAllOrders();
}
