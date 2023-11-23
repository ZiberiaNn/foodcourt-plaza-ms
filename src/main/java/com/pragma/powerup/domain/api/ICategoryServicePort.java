package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryServicePort {
    CategoryModel saveCategory(CategoryModel categoryModel);
    CategoryModel getCategoryById(Long id);
    List<CategoryModel> getAllCategories();
}
