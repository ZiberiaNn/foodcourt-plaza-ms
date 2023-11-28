package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.auth.UserModel;
import com.pragma.powerup.domain.model.enums.StatusEnum;
import com.pragma.powerup.domain.spi.*;
import com.pragma.powerup.infrastructure.out.feign.request.SmsRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
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
    private final ISmsPersistencePort smsPersistencePort;
    private static final int PIN_LENGTH=4;

    @Override
    public OrderModel createOrder(OrderModel orderModel, String loggedUserEmail) {
        checkIfDishesBelongsToRestaurant(orderModel);
        UserModel client = userPersistencePort.getUserByEmail(loggedUserEmail);
        checkIfClientHasOrdersInProcess(client);
        orderModel.setStatus(StatusEnum.PENDIENTE);
        orderModel.setClientIdentityNumber(client.getIdentityDocument());
        orderModel.setPin(RandomStringUtils.randomAlphabetic(PIN_LENGTH));
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
    @Override
    public OrderModel updateOrderAssignedEmployeeAndStatusToEnPreparacion(Long existingOrderId, String loggedUserEmail) {
        OrderModel orderModel = orderPersistencePort.getOrderById(existingOrderId);
        RestaurantEmployeeModel restaurantEmployee = restaurantEmployeePersistencePort.getEmployeeByEmail(loggedUserEmail);
        checkIfOrderBelongsToEmployeeRestaurant(orderModel, restaurantEmployee);
        checkIfOrderIsNotDelivered(orderModel);
        orderModel.setStatus(StatusEnum.EN_PREPARACION);
        orderModel.setAssignedEmployee(restaurantEmployee);
        return orderPersistencePort.updateOrder(orderModel);
    }

    @Override
    public OrderModel updateOrderStatusToDoneAndSendSms(Long existingOrderId, String authToken, String loggedUserEmail) {
        OrderModel orderModel = orderPersistencePort.getOrderById(existingOrderId);
        checkIfOrderBelongsToEmployeeRestaurant(orderModel, restaurantEmployeePersistencePort.getEmployeeByEmail(loggedUserEmail));
        orderModel.setStatus(StatusEnum.LISTO);
        UserModel client = userPersistencePort.getUserByIdentityNumber(orderModel.getClientIdentityNumber());
        smsPersistencePort.sendSms(
                new SmsRequest(
                        "¡Hola, "+client.getName()+"! Te informamos que tu pedido #"+ existingOrderId +" esta listo. Reclamalo con el pin: "+orderModel.getPin(),
                        client.getPhone()),
                authToken
        );
        return orderPersistencePort.updateOrder(orderModel);
    }
    @Override
    public OrderModel updateOrderStatusToDelivered(Long existingOrderId, String pin, String loggedUserEmail) {
        OrderModel orderModel = orderPersistencePort.getOrderById(existingOrderId);
        checkIfOrderBelongsToEmployeeRestaurant(orderModel, restaurantEmployeePersistencePort.getEmployeeByEmail(loggedUserEmail));
        checkIfOrderIsNotDelivered(orderModel);
        if(orderModel.getStatus()!=StatusEnum.LISTO){
            throw new DomainException("Only orders with status 'LISTO' can be delivered");
        }
        if(!orderModel.getPin().equals(pin)){
            throw new DomainException("The pin is not correct");
        }
        orderModel.setStatus(StatusEnum.ENTREGADO);
        return orderPersistencePort.updateOrder(orderModel);
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
    private void checkIfOrderBelongsToEmployeeRestaurant(OrderModel orderModel, RestaurantEmployeeModel restaurantEmployee) {
        if(!Objects.equals(orderModel.getRestaurant().getId(), restaurantEmployee.getRestaurant().getId())){
            throw new DomainException("The order does not belong to the employee's restaurant");
        }
    }

    private void checkIfOrderIsNotDelivered(OrderModel orderModel) {
         if(orderModel.getStatus().getName().equalsIgnoreCase(StatusEnum.ENTREGADO.getName())){
            throw new DomainException("The order is already delivered and the status cannot be changed");
         }
    }
}