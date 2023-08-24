package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.dish.DishRequestDto;
import com.reto.plazoleta.application.dto.request.dish.DishUpdateRequestDto;
import com.reto.plazoleta.application.dto.response.DishResponse;

import java.util.List;

public interface IDishHandler {
    void saveDish(DishRequestDto dishRequestDto);

    void updateDish(DishUpdateRequestDto dishUpdateRequestDto);

    void enableDish(Long id, boolean isActive);

    List<DishResponse> getRestaurantDishes(Long idRestaurant, int nElements, String category);

}
