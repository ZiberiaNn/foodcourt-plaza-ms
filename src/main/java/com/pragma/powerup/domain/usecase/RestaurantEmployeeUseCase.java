package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantEmployeeServicePort;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantEmployeeUseCase implements IRestaurantEmployeeServicePort {
    private final IRestaurantEmployeeServicePort restaurantEmployeeServicePort;
    @Override
    public RestaurantEmployeeModel saveEmployee(RestaurantEmployeeModel restaurantEmployeeModel) {
        return restaurantEmployeeServicePort.saveEmployee(restaurantEmployeeModel);
    }
}
