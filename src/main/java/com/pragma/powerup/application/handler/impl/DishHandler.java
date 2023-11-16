package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishDescPriceRequestDto;
import com.pragma.powerup.application.dto.request.DishIsActiveRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishDescPriceRequestMapper;
import com.pragma.powerup.application.mapper.IDishIsActiveMapper;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishResponseMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.DishModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final IDishIsActiveMapper dishIsActiveMapper;


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
    public Page<DishResponseDto> getDishesByRestaurantAndCategory(Long restaurantId, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dishServicePort.getDishesByRestaurantAndCategory(restaurantId, category, pageable).map(dishResponseMapper::toResponse);
    }

    @Override
    public DishResponseDto updateDishDescAndPrice(Long dishId, DishDescPriceRequestDto dishRequestDto) {
        DishModel dishModel = dishDescPriceRequestMapper.toModel(dishRequestDto);
        return dishResponseMapper.toResponse(dishServicePort.partialUpdateDishModel(dishId,dishModel));
    }

    @Override
    public DishResponseDto updateDishIsActive(Long dishId, DishIsActiveRequestDto dishIsActiveRequestDto) {
        DishModel dishModel = dishIsActiveMapper.toModel(dishIsActiveRequestDto);
        return dishResponseMapper.toResponse(dishServicePort.partialUpdateDishModel(dishId,dishModel));
    }
}