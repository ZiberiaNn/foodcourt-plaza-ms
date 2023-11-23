package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Override
    public CategoryModel saveCategory(CategoryModel categoryModel) {
        CategoryEntity categoryEntity = categoryRepository.save(categoryEntityMapper.toEntity(categoryModel));
        return categoryEntityMapper.toModel(categoryEntity);
    }

    @Override
    public CategoryModel getCategoryById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NoDataFoundException("No category found with id: " + id);
        }
        return categoryEntityMapper.toModel(entity.get());
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if (categoryEntityList.isEmpty()) {
            throw new NoDataFoundException("No categories found");
        }
        return categoryEntityMapper.toModelList(categoryEntityList);
    }
}
