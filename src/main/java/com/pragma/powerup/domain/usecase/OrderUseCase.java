package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IDishPersistencePort dishPersistencePort;

    @Override
    public OrderModel saveModel(OrderModel orderModel) {
        orderModel.getDishQtyList().forEach(orderDishQtyEntity -> {
            if(!Objects.equals(
                    dishPersistencePort.getDishById(orderDishQtyEntity.getDish().getId()).getRestaurant().getId(),
                    orderModel.getRestaurant().getId())
            ){
                throw new DomainException("At least one dish does not belong to restaurant");
            }
        });
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