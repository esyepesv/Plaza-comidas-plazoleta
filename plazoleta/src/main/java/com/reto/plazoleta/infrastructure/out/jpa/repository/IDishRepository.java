package com.reto.plazoleta.infrastructure.out.jpa.repository;

import com.reto.plazoleta.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
}
