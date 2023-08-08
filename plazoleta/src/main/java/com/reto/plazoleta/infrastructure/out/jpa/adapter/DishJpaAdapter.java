package com.reto.plazoleta.infrastructure.out.jpa.adapter;

import com.reto.plazoleta.domain.model.DishModel;
import com.reto.plazoleta.domain.spi.IDishPersistencePort;
import com.reto.plazoleta.infrastructure.out.jpa.entity.DishEntity;
import com.reto.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public void saveDish(DishModel dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }
}
