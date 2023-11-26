package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.DishModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDishServicePort {
    DishModel saveDish(DishModel dishModel);

    DishModel partialUpdateDishModel(Long dishId, DishModel dishModel);

    List<DishModel> getAllDishes();

    Page<DishModel> getDishesByRestaurantAndCategory(Long restaurantId, String category, Pageable pageable);
}
