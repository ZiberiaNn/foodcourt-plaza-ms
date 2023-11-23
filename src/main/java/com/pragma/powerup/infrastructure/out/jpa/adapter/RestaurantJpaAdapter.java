package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;


    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        RestaurantEntity restaurantEntity = restaurantRepository.save(restaurantEntityMapper.toEntity(restaurantModel));
        return restaurantEntityMapper.toModel(restaurantEntity);
    }

    @Override
    public Page<RestaurantModel> getRestaurantsOrderedByName(Pageable pageable) {
        Page<RestaurantEntity> entityPage = restaurantRepository.findAllByOrderByNameAsc(pageable);
        if (entityPage.isEmpty()) {
            throw new NoDataFoundException("No restaurants found");
        }
        return entityPage.map(restaurantEntityMapper::toModel);
    }
    @Override
    public RestaurantModel getRestaurantById(Long id) {
        Optional<RestaurantEntity> entity = restaurantRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NoDataFoundException("No restaurant found with id: " + id);
        }
        return restaurantEntityMapper.toModel(entity.get());
    }
}