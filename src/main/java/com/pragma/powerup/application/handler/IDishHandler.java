package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishDescPriceRequestDto;
import com.pragma.powerup.application.dto.request.DishIsActiveRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDishHandler {
    DishResponseDto saveDish(DishRequestDto dishRequestDto);

    List<DishResponseDto> getAllDishes();

    Page<DishResponseDto> getDishesByRestaurantAndCategory(Long restaurantId, String category, int page, int size);

    DishResponseDto updateDishDescAndPrice(Long dishId, DishDescPriceRequestDto dishRequestDto);

    DishResponseDto updateDishIsActive(Long dishId, DishIsActiveRequestDto dishIsActiveRequestDto);
}