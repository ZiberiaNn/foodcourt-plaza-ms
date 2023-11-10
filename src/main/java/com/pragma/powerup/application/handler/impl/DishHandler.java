package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishResponseMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public DishResponseDto saveDish(DishRequestDto dishRequestDto) {
        DishModel dishModel = dishRequestMapper.toModel(dishRequestDto);
        dishModel.setRestaurant(RestaurantModel.builder().id(dishRequestDto.getRestaurantId()).build());
        return dishResponseMapper.toResponse(dishServicePort.saveDish(dishModel));
    }
    @Override
    public List<DishResponseDto> getAllDishes() {
        return dishResponseMapper.toResponseList(dishServicePort.getAllDishes());
    }
}