package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.CategoryRequestDto;
import com.pragma.powerup.application.dto.response.CategoryResponseDto;
import com.pragma.powerup.application.handler.ICategoryHandler;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content)
    })
    @PreAuthorize("hasRole(" +
            "T(com.pragma.powerup.domain.model.auth.enums.RoleEnum).ADMIN.toString()" +
            ")")
    @PostMapping("/")
    public ResponseEntity<CategoryResponseDto> saveCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryHandler.saveCategory(categoryRequestDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All categories returned",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryHandler.getAllCategories());
    }

    @Operation(summary = "Get one category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "One category returned",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(
            @Schema(example = "1") @PathVariable(name = "id") Long id
    ) {
        return ResponseEntity.ok(categoryHandler.getCategoryById(id));
    }
}