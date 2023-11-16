package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.RestaurantModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantPersistencePort {
    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);
    List<RestaurantModel> getRestaurantsOrderedByName(Pageable pageable);

    RestaurantModel getRestaurantById(Long id);
}
