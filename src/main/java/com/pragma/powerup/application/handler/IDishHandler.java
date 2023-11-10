package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {
    DishResponseDto saveDish(DishRequestDto dishRequestDto);

    List<DishResponseDto> getAllDishes();
}