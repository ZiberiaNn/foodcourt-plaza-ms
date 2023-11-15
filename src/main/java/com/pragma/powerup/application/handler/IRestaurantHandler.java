package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantGetResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantSavedResponseDto;

import java.util.List;

public interface IRestaurantHandler {

    RestaurantSavedResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto);


    List<RestaurantGetResponseDto> getRestaurantsOrderedByName(int page, int size);
}