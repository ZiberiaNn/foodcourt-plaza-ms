package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.CategoryRequestDto;
import com.pragma.powerup.application.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {
    CategoryResponseDto saveCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getCategoryById(Long id);

    List<CategoryResponseDto> getAllCategories();
}
