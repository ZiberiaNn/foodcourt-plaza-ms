package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;

import com.pragma.powerup.domain.exception.UserNotOwnerException;
import com.pragma.powerup.domain.exception.invalid.InvalidNameException;
import com.pragma.powerup.domain.exception.invalid.InvalidNitException;
import com.pragma.powerup.domain.exception.invalid.InvalidPhoneException;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.auth.RoleModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.regex.Pattern;
@AllArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    private static final String NOT_EXCLUSIVELY_NUMBER_PATTERN =
            "(?!^\\d+$)^.+$";
    private static final Pattern notExclusivelyNumericPattern = Pattern.compile(NOT_EXCLUSIVELY_NUMBER_PATTERN);

    private static final String EXCLUSIVELY_NUMERIC_PATTERN =
            "^\\d+$";
    private static final Pattern exclusivelyNumericPattern = Pattern.compile(EXCLUSIVELY_NUMERIC_PATTERN);
    private static final String PHONE_PATTERN =
            "^\\+\\d{1,12}$";
    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        if(!phonePattern.matcher(restaurantModel.getPhone()).matches()){
            throw new InvalidPhoneException();
        } else if (!exclusivelyNumericPattern.matcher(restaurantModel.getNit()).matches()){
            throw new InvalidNitException();
        } else if (!notExclusivelyNumericPattern.matcher(restaurantModel.getName()).matches()){
            throw new InvalidNameException();
        }
        boolean isUserOwner = false;
        for(RoleModel userRole : userPersistencePort.getUserByIdentityNumber(restaurantModel.getOwnerUserIdentityNumber()).getRoles()){
            if(userRole.getName().equals(RoleModel.RoleEnum.OWNER.getName())){
                isUserOwner = true;
                break;
            }
        }
        if(!isUserOwner) {
            throw new UserNotOwnerException();
        }
        return restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }
}