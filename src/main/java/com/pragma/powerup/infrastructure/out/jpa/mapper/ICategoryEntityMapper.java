package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(CategoryModel model);
    CategoryModel toModel(CategoryEntity entity);
    List<CategoryModel> toModelList(List<CategoryEntity> entityList);
}
