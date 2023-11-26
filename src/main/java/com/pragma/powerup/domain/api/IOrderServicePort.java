package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderServicePort {
    OrderModel createOrder(OrderModel orderModel, String loggedUserEmail);
    OrderModel getOrderById(Long id);
    List<OrderModel> getAllOrders();
    Page<OrderModel> getOrdersByStatusAndIfEmployeeBelongsToOrder(String status, Pageable pageable, String loggedUserEmail) ;
}
