package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.domain.model.enums.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final IOrderHandler orderHandler;
    @Operation(summary = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Order already exists", content = @Content)
    })
    @PreAuthorize("hasRole(" +
            "T(com.pragma.powerup.domain.model.auth.enums.RoleEnum).CLIENT.toString()" +
            ")")
    @PostMapping("/")
    public ResponseEntity<OrderResponseDto> saveOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return new ResponseEntity<>(orderHandler.saveOrder(orderRequestDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All orders returned",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return ResponseEntity.ok(orderHandler.getAllOrders());
    }

    @Operation(summary = "Get one order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "One order returned",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(
            @Schema(example = "1") @PathVariable(name = "id") Long id
    ) {
        return ResponseEntity.ok(orderHandler.getOrderById(id));
    }

    @Operation(summary = "Get all orders filtered by status and if the logged employee belongs to the order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return orders filtered by status and if the logged employee belongs to the order",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @PreAuthorize("hasRole(" +
            "T(com.pragma.powerup.domain.model.auth.enums.RoleEnum).EMPLOYEE.toString()" +
            ")")
    @GetMapping("/by-status/{status}")
    public ResponseEntity<Page<OrderResponseDto>> getOrdersByStatusAndIfEmployeeBelongsToOrder(
            @PathVariable(name = "status") StatusEnum status,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size
    ) {
        return ResponseEntity.ok(orderHandler.getOrdersByStatusAndIfEmployeeBelongsToOrder(status, page, size));
    }
}