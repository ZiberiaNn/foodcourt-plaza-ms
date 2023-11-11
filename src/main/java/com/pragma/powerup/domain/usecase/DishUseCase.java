package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class DishUseCase implements IDishServicePort {
    IDishPersistencePort dishPersistencePort;
    @Override
    public DishModel saveDish(DishModel dishModel) {
        return dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel updateDishDescAndPrice(Long dishId, DishModel dishModel) {
        return dishPersistencePort.updateDish(dishId, dishModel);
    }

    @Override
    public List<DishModel> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }
}