package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    @Query("SELECT d FROM DishEntity d WHERE d.restaurant.id = :restaurantId AND (:category IS NULL OR d.category = :category)")
    Page<DishEntity> findByRestaurantAndCategory(Long restaurantId, String category, Pageable pageable);
}
