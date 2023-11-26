package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantEmployeeServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.auth.RoleModel;
import com.pragma.powerup.domain.spi.IRestaurantEmployeePersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantEmployeeUseCase implements IRestaurantEmployeeServicePort {
    private final IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;
    private final IUserPersistencePort userPersistencePort;
    @Override
    public RestaurantEmployeeModel saveEmployee(RestaurantEmployeeModel restaurantEmployeeModel) {
        checkIfRestaurantEmployeeHasEmployeeRole(restaurantEmployeeModel.getEmail());
        return restaurantEmployeePersistencePort.saveEmployee(restaurantEmployeeModel);
    }
    private void checkIfRestaurantEmployeeHasEmployeeRole(String restaurantEmployeeEmail) {
        boolean isUserEmployee = false;
        for(RoleModel userRole : userPersistencePort.getUserByEmail(restaurantEmployeeEmail).getRoles()){
            if(userRole.getName().equals(RoleModel.RoleEnum.EMPLOYEE.getName())){
                isUserEmployee = true;
                break;
            }
        }
        if(!isUserEmployee) {
            throw new DomainException("User is not an employee or does not exist");
        }
    }
}
