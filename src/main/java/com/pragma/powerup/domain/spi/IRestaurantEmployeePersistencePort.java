package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.RestaurantEmployeeModel;

public interface IRestaurantEmployeePersistencePort {
    RestaurantEmployeeModel saveEmployee(RestaurantEmployeeModel restaurantEmployeeModel);
    RestaurantEmployeeModel getEmployeeByEmail(String email);
}
