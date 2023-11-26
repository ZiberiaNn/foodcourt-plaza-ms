package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.auth.UserModel;
import com.pragma.powerup.domain.model.enums.StatusEnum;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantEmployeePersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IUserPersistencePort userPersistencePort;
    private final IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;

    @Override
    public OrderModel createOrder(OrderModel orderModel, String loggedUserEmail) {
        checkIfDishesBelongsToRestaurant(orderModel);
        UserModel client = userPersistencePort.getUserByEmail(loggedUserEmail);
        checkIfClientHasOrdersInProcess(client);
        orderModel.setStatus(StatusEnum.PENDIENTE);
        orderModel.setClientIdentityNumber(client.getIdentityDocument());
        return orderPersistencePort.saveOrder(orderModel);
    }

    private void checkIfDishesBelongsToRestaurant(OrderModel orderModel) {
        orderModel.getDishQtyList().forEach(orderDishQtyEntity -> {
            if(!Objects.equals(
                    dishPersistencePort.getDishById(orderDishQtyEntity.getDish().getId()).getRestaurant().getId(),
                    orderModel.getRestaurant().getId())
            ){
                throw new DomainException("At least one dish does not belong to restaurant");
            }
        });
    }
    private void checkIfClientHasOrdersInProcess(UserModel client) {
        orderPersistencePort.getOrderByClientIdentityNumber(client.getIdentityDocument()).forEach(clientOrder -> {
            if(clientOrder.getStatus().getName().equalsIgnoreCase(StatusEnum.EN_PREPARACION.getName())||
                    clientOrder.getStatus().getName().equalsIgnoreCase(StatusEnum.PENDIENTE.getName())){
                throw new DomainException("Client has an order in process");
            }
        });
    }

    @Override
    public OrderModel getOrderById(Long id) {
        return orderPersistencePort.getOrderById(id);
    }
    @Override
    public List<OrderModel> getAllOrders() {
        return orderPersistencePort.getAllOrders();
    }
    @Override
    public Page<OrderModel> getOrdersByStatusAndIfEmployeeBelongsToOrder(String status, Pageable pageable, String loggedUserEmail) {
        RestaurantEmployeeModel restaurantEmployee = restaurantEmployeePersistencePort.getEmployeeByEmail(loggedUserEmail);
        Page<OrderModel> fullOrderPage = orderPersistencePort.getOrdersByStatus(status, pageable);
        Page<OrderModel> filteredOrderPage = new PageImpl<>(fullOrderPage.stream().filter(order -> Objects.equals(order.getRestaurant().getId(), restaurantEmployee.getRestaurant().getId()))
                .collect(Collectors.toList()), fullOrderPage.getPageable(), fullOrderPage.getTotalElements());
        if (filteredOrderPage.isEmpty()) {
            throw new DomainException("The orders found are not from the employee's restaurant");
        }
        return filteredOrderPage;
    }
}