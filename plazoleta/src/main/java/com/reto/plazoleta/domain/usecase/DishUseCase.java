package com.reto.plazoleta.domain.usecase;

import com.reto.plazoleta.domain.api.IDishServicePort;
import com.reto.plazoleta.domain.model.DishModel;
import com.reto.plazoleta.domain.spi.IDishPersistencePort;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void saveDish(DishModel dish) {
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public DishModel getDish(Long id) {
        return dishPersistencePort.getDish(id);
    }

    @Override
    public void updateDish(DishModel dish) {
        dishPersistencePort.updateDish(dish);
    }

    @Override
    public List<DishModel> getRestaurantDishes(Long idRestaurant) {
        return dishPersistencePort.getRestaurantDishes(idRestaurant);
    }
}
