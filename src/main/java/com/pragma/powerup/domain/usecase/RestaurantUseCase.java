package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;

import com.pragma.powerup.domain.exception.invalid.InvalidNameException;
import com.pragma.powerup.domain.exception.invalid.InvalidNitException;
import com.pragma.powerup.domain.exception.invalid.InvalidPhoneException;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;

import java.util.List;
import java.util.regex.Pattern;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private static final String NOT_EXCLUSIVELY_NUMBER_PATTERN =
            "(?!^\\d+$)^.+$";
    private static final Pattern notExclusivelyNumericPattern = Pattern.compile(NOT_EXCLUSIVELY_NUMBER_PATTERN);

    private static final String EXCLUSIVELY_NUMERIC_PATTERN =
            "^\\d+$";
    private static final Pattern exclusivelyNumericPattern = Pattern.compile(EXCLUSIVELY_NUMERIC_PATTERN);
    private static final String PHONE_PATTERN =
            "^\\+\\d{1,12}$";
    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        if(!phonePattern.matcher(restaurantModel.getPhone()).matches()){
            throw new InvalidPhoneException();
        } else if (!exclusivelyNumericPattern.matcher(restaurantModel.getNit()).matches()){
            throw new InvalidNitException();
        } else if (!notExclusivelyNumericPattern.matcher(restaurantModel.getName()).matches()){
            throw new InvalidNameException();
        }
        return restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }
}