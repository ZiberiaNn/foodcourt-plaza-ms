package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishQtyKey;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderDishQtyRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderDishQtyRepository orderDishQtyRepository;

    @Override
    public OrderModel saveOrder(OrderModel orderModel) {
        OrderEntity savedOrderEntity = orderRepository.save(orderEntityMapper.toEntity(orderModel));
        savedOrderEntity.getDishQtyList().forEach(orderDishQtyEntity -> {
            orderDishQtyEntity.setId(new OrderDishQtyKey(savedOrderEntity.getId(), orderDishQtyEntity.getDish().getId()));
            orderDishQtyEntity.setOrder(savedOrderEntity);
            orderDishQtyRepository.
                    save(orderDishQtyEntity);
        });
        return orderEntityMapper.toModel(savedOrderEntity);
    }
    @Override
    public OrderModel getOrderById(Long id) {
        Optional<OrderEntity> entity = orderRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NoDataFoundException("No order found with id: " + id);
        }
        return orderEntityMapper.toModel(entity.get());
    }
    @Override
    public List<OrderModel> getAllOrders() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        if (orderEntityList.isEmpty()) {
            throw new NoDataFoundException("No orders found");
        }
        return orderEntityMapper.toModelList(orderEntityList);
    }
}