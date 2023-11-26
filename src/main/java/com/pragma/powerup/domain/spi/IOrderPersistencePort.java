package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderPersistencePort {
    OrderModel saveOrder(OrderModel orderModel);
    OrderModel getOrderById(Long id);


    List<OrderModel> getOrderByClientIdentityNumber(Integer clientIdentityNumber);

    List<OrderModel> getAllOrders();

    Page<OrderModel> getOrdersByStatus(String status, Pageable pageable);
}
