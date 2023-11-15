package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {
    DishModel saveDish(DishModel dishModel);

    DishModel partialUpdateDishModel(Long dishId, DishModel dishModel);

    List<DishModel> getAllDishes();
}
