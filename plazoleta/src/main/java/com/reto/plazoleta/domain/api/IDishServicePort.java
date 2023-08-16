package com.reto.plazoleta.domain.api;

import com.reto.plazoleta.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {
    void saveDish(DishModel dish);
    DishModel getDish(Long id);
    void updateDish(DishModel dish);
    List<DishModel> getRestaurantDishes(Long idRestaurant);

}
