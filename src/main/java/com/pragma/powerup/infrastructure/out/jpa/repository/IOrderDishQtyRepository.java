package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishQtyEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishQtyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDishQtyRepository extends JpaRepository<OrderDishQtyEntity, OrderDishQtyKey> {
}
