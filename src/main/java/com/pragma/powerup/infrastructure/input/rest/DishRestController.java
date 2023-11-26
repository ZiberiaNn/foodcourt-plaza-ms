package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishDescPriceRequestDto;
import com.pragma.powerup.application.dto.request.DishIsActiveRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DishResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Dish already exists", content = @Content)
    })
    @PreAuthorize("hasRole(" +
            "T(com.pragma.powerup.domain.model.auth.enums.RoleEnum).OWNER.toString()" +
            ")")
    @PostMapping("/")
    public ResponseEntity<DishResponseDto> saveDish(@RequestBody DishRequestDto dishRequestDto) {
        return new ResponseEntity<>(dishHandler.saveDish(dishRequestDto), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole(" +
            "T(com.pragma.powerup.domain.model.auth.enums.RoleEnum).OWNER.toString()" +
            ")")
    @Operation(summary = "Enable or disable a dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DishResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found with entered dishId", content = @Content)
    })
    @PatchMapping("/{dishId}/isActive")
    public ResponseEntity<DishResponseDto> updateDishIsActive(
            @Schema(example = "1") @PathVariable(name = "dishId") Long dishId,
            @RequestBody DishIsActiveRequestDto dishIsActiveRequestDto
    ) {
        return new ResponseEntity<>(dishHandler.updateDishIsActive(dishId ,dishIsActiveRequestDto), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole(" +
            "T(com.pragma.powerup.domain.model.auth.enums.RoleEnum).OWNER.toString()" +
            ")")
    @Operation(summary = "Update the price and description of an existing dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DishResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found with entered dishId", content = @Content)
    })
    @PatchMapping("/{dishId}")
    public ResponseEntity<DishResponseDto> updateDishDescAndPrice(
            @Schema(example = "1") @PathVariable(name = "dishId") Long dishId,
            @RequestBody DishDescPriceRequestDto dishDescPriceRequestDto
    ) {
        return new ResponseEntity<>(dishHandler.updateDishDescAndPrice(dishId ,dishDescPriceRequestDto), HttpStatus.CREATED);
    }
    @Operation(summary = "Get all dishes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All dishes returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DishResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<DishResponseDto>> getAllDishes() {
        return ResponseEntity.ok(dishHandler.getAllDishes());
    }

}