package com.reto.plazoleta.infrastructure.out.jpa.adapter;

import com.reto.plazoleta.domain.model.DishModel;
import com.reto.plazoleta.domain.spi.IDishPersistencePort;
import com.reto.plazoleta.infrastructure.exception.NoDataFoundException;
import com.reto.plazoleta.infrastructure.out.jpa.entity.DishEntity;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public void saveDish(DishModel dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public DishModel getDish(Long id) {
        return dishEntityMapper.toDish(dishRepository.findById(id)
                .orElseThrow(NoDataFoundException::new));
    }

    @Override
    public void updateDish(DishModel dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public List<DishModel> getRestaurantDishes(Long idRestaurant) {
        List<DishEntity> dishEntityList = dishRepository.findAllByIdRestaurant(idRestaurant);
        if (dishEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return dishEntityMapper.toDishList(dishEntityList);
    }

    @Override
    public List<DishModel> getDishes() {
        return dishEntityMapper.toDishList(dishRepository.findAll());
    }
}
