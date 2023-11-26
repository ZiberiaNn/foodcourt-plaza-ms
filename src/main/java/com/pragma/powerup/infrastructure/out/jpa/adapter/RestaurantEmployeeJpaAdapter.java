package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.spi.IRestaurantEmployeePersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEmployeeEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
@RequiredArgsConstructor
public class RestaurantEmployeeJpaAdapter implements IRestaurantEmployeePersistencePort {
    private final IRestaurantEmployeeRepository restaurantRepository;
    private final IRestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;
    @Override
    public RestaurantEmployeeModel saveEmployee(RestaurantEmployeeModel restaurantEmployeeModel) {
        RestaurantEmployeeEntity restaurantEmployeeEntity = restaurantRepository.save(restaurantEmployeeEntityMapper.toEntity(restaurantEmployeeModel));
        return restaurantEmployeeEntityMapper.toModel(restaurantEmployeeEntity);
    }

    @Override
    public RestaurantEmployeeModel getEmployeeByEmail(String email) {
        Optional<RestaurantEmployeeEntity> entity = restaurantRepository.findByEmailIgnoreCase(email);
        if (entity.isEmpty()) {
            throw new NoDataFoundException("No employee found with email: " + email);
        }
        return restaurantEmployeeEntityMapper.toModel(entity.get());
    }

}
