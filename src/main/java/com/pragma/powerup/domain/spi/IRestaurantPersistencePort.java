package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.RestaurantModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRestaurantPersistencePort {
    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);
    Page<RestaurantModel> getRestaurantsOrderedByName(Pageable pageable);

    RestaurantModel getRestaurantById(Long id);
}
