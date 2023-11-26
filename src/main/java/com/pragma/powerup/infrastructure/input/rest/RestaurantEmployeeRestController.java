package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantEmployeeResponseDto;
import com.pragma.powerup.application.handler.IRestaurantEmployeeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants/employees")
@RequiredArgsConstructor
public class RestaurantEmployeeRestController {

    private final IRestaurantEmployeeHandler restaurantEmployeeHandler;
    @PreAuthorize("hasRole(" +
            "T(com.pragma.powerup.domain.model.auth.enums.RoleEnum).OWNER.toString()" +
            ")")
    @Operation(summary = "Add a new employee to a restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee added to restaurant",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantEmployeeResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Employee is already added", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<RestaurantEmployeeResponseDto> addEmployeeToRestaurant(@RequestBody RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        return new ResponseEntity<>(restaurantEmployeeHandler.addEmployeeToRestaurant(restaurantEmployeeRequestDto), HttpStatus.CREATED);
    }
}
