package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.DishModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDishPersistencePort {
    DishModel saveDish(DishModel dishModel);

    DishModel partialUpdateDish(Long id, DishModel newDishModel);

    List<DishModel> getAllDishes();

    Page<DishModel> getDishesByRestaurantAndCategory(Long id, String category, Pageable pageable);

    DishModel getDishById(Long id);
}
