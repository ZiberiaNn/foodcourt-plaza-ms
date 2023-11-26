package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.exception.UserNotOwnerException;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.auth.UserModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        checkLoggedUserOwnershipOfRestaurant(dishModel);
        return dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel partialUpdateDishModel(Long dishId, DishModel dishModel) {
        DishModel existingDishModel = dishPersistencePort.getDishById(dishId);
        checkLoggedUserOwnershipOfRestaurant(existingDishModel);
        return dishPersistencePort.partialUpdateDish(dishId, dishModel);
    }

    @Override
    public List<DishModel> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public Page<DishModel> getDishesByRestaurantAndCategory(Long restaurantId, String category, Pageable pageable) {
        return dishPersistencePort.getDishesByRestaurantAndCategory(restaurantId, category, pageable);
    }

    private void checkLoggedUserOwnershipOfRestaurant(DishModel restaurantDish) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserModel loggedUserModel = userPersistencePort.getUserByEmail(loggedUser.getUsername());
        if(!Objects.equals(loggedUserModel.getIdentityDocument(), restaurantDish.getRestaurant().getOwnerUserIdentityNumber())){
            throw new UserNotOwnerException();
        }
    }
}