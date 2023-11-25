package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.auth.UserModel;
import com.pragma.powerup.domain.model.enums.StatusEnum;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        checkIfDishesBelongsToRestaurant(orderModel);
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserModel client = userPersistencePort.getUserByEmail(loggedUser.getUsername());
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
}