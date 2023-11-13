package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishDescPriceRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishDescPriceRequestMapper;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishResponseMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.DishModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishDescPriceRequestMapper dishDescPriceRequestMapper;

    private final IDishResponseMapper dishResponseMapper;

    @Override
    public DishResponseDto saveDish(DishRequestDto dishRequestDto) {
        DishModel dishModel = dishRequestMapper.toModel(dishRequestDto);
        dishModel.setRestaurant(restaurantServicePort.getRestaurantById(dishModel.getRestaurant().getId()));
        return dishResponseMapper.toResponse(dishServicePort.saveDish(dishModel));
    }
    @Override
    public List<DishResponseDto> getAllDishes() {
        return dishResponseMapper.toResponseList(dishServicePort.getAllDishes());
    }
    @Override
    public DishResponseDto updateDishDescAndPrice(Long dishId, DishDescPriceRequestDto dishRequestDto) {
        DishModel dishModel = dishDescPriceRequestMapper.toModel(dishRequestDto);
        return dishResponseMapper.toResponse(dishServicePort.updateDishDescAndPrice(dishId,dishModel));
    }
}