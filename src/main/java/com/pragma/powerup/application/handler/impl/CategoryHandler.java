package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.CategoryRequestDto;
import com.pragma.powerup.application.dto.response.CategoryResponseDto;
import com.pragma.powerup.application.handler.ICategoryHandler;
import com.pragma.powerup.application.mapper.*;
import com.pragma.powerup.domain.api.ICategoryServicePort;
import com.pragma.powerup.domain.model.CategoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Override
    public CategoryResponseDto saveCategory(CategoryRequestDto categoryRequestDto) {
        CategoryModel categoryModel = categoryRequestMapper.toModel(categoryRequestDto);
        return categoryResponseMapper.toResponse(categoryServicePort.saveCategory(categoryModel));
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        return categoryResponseMapper.toResponse(categoryServicePort.getCategoryById(id));
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategories());
    }
}
