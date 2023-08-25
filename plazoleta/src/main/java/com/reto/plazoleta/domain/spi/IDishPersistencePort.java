package com.reto.plazoleta.domain.spi;

import com.reto.plazoleta.domain.model.DishModel;

import java.util.List;

public interface IDishPersistencePort {
    void saveDish(DishModel dish);
    DishModel getDish(Long id);
    void updateDish(DishModel dish);
    List<DishModel> getRestaurantDishes(Long idRestaurant);
    List<DishModel> getDishes();

}
