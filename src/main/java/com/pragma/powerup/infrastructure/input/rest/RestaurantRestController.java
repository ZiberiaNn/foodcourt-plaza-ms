package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantGetResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantSavedResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;
    private final IDishHandler dishHandler;
    @PreAuthorize("hasRole(" +
            "T(com.pragma.powerup.domain.model.auth.enums.RoleEnum).ADMIN.toString()" +
            ")")
    @Operation(summary = "Add a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantSavedResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<RestaurantSavedResponseDto> saveRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto) {
        return new ResponseEntity<>(restaurantHandler.saveRestaurant(restaurantRequestDto), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole(" +
            "T(com.pragma.powerup.domain.model.auth.enums.RoleEnum).CLIENT.toString()" +
            ")")
    @Operation(summary = "Get all restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All restaurants returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RestaurantGetResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<RestaurantGetResponseDto>> getRestaurantsOrderedByName(@RequestParam("page") int page,
                                                                                                  @RequestParam("size") int size) {
        return ResponseEntity.ok(restaurantHandler.getRestaurantsOrderedByName(page, size));
    }
    @PreAuthorize("hasRole(" +
            "T(com.pragma.powerup.domain.model.auth.enums.RoleEnum).CLIENT.toString()" +
            ")")
    @Operation(summary = "Get all dishes from a restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All dishes from restaurant returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DishResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{restaurantId}/dishes")
    public ResponseEntity<Page<DishResponseDto>> getDishesFromRestaurantAndCategory(
            @Schema(example = "1") @PathVariable(name = "restaurantId") Long restaurantId,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return ResponseEntity.ok(dishHandler.getDishesByRestaurantAndCategory(restaurantId, category, page, size));
    }
}