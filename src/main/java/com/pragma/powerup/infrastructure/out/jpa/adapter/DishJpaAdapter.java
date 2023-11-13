package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public DishModel saveDish(DishModel dishModel) {
        DishEntity dishEntity = dishRepository.save(dishEntityMapper.toEntity(dishModel));
        return dishEntityMapper.toModel(dishEntity);
    }
    @Override
    public DishModel partialUpdateDish(Long id, DishModel newDishModel) {
        return dishRepository.findById(id).map(dishEntity -> {
            dishEntityMapper.partialUpdate(newDishModel, dishEntity);
            return dishEntityMapper.toModel(dishRepository.save(dishEntity));
        }).orElseThrow(NoDataFoundException::new);
    }

    @Override
    public List<DishModel> getAllDishes() {
        List<DishEntity> dishEntityList = dishRepository.findAll();
        if (dishEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return dishEntityMapper.toModelList(dishEntityList);
    }
}