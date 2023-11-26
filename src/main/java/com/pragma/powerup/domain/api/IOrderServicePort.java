package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderServicePort {
    OrderModel createOrder(OrderModel orderModel);
    OrderModel getOrderById(Long id);
    List<OrderModel> getAllOrders();
    Page<OrderModel> getOrdersByStatus(String status, Pageable pageable);
}
