package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.exception.UserNotOwnerException;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.auth.UserModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Objects;


@AllArgsConstructor
public class DishUseCase implements IDishServicePort {
    IDishPersistencePort dishPersistencePort;
    IUserPersistencePort userPersistencePort;
    @Override
    public DishModel saveDish(DishModel dishModel) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        checkLoggedUserOwnership(dishModel, loggedUser);
        return dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel updateDishDescAndPrice(Long dishId, DishModel dishModel) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DishModel existingDishModel = dishPersistencePort.getDishById(dishId);
        checkLoggedUserOwnership(existingDishModel, loggedUser);
        return dishPersistencePort.partialUpdateDish(dishId, dishModel);
    }

    @Override
    public List<DishModel> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }
    private void checkLoggedUserOwnership(DishModel dishModel, User loggedUser) {
        UserModel loggedUserModel = userPersistencePort.getUserByEmail(loggedUser.getUsername());
        if(!Objects.equals(loggedUserModel.getIdentityDocument(), dishModel.getRestaurant().getOwnerUserIdentityNumber())){
            throw new UserNotOwnerException();
        }
    }
}