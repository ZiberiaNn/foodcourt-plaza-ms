package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantGetResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantSavedResponseDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.application.mapper.IRestaurantGetResponseMapper;
import com.pragma.powerup.application.mapper.IRestaurantRequestMapper;
import com.pragma.powerup.application.mapper.IRestaurantSavedResponseMapper;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantSavedResponseMapper restaurantSavedResponseMapper;
    private final IRestaurantGetResponseMapper restaurantGetResponseMapper;


    @Override
    public RestaurantSavedResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantModel restaurantModel = restaurantRequestMapper.toModel(restaurantRequestDto);
        return restaurantSavedResponseMapper.toResponse(restaurantServicePort.saveRestaurant(restaurantModel));
    }

    @Override
    public Page<RestaurantGetResponseDto> getRestaurantsOrderedByName(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return restaurantServicePort.getRestaurantsOrderedByName(pageable).map(restaurantGetResponseMapper::toResponse);
    }
}