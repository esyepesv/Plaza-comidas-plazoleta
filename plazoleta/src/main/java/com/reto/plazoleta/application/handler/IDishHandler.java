package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.dish.DishRequestDto;
import com.reto.plazoleta.application.dto.request.dish.DishUpdateRequestDto;
import com.reto.plazoleta.application.dto.response.DishResponse;

import java.util.List;

public interface IDishHandler {
    void saveDish(DishRequestDto dishRequestDto, Long idOwner);

    void updateDish(DishUpdateRequestDto dishUpdateRequestDto, Long idOwner);

    void enableDish(Long id, boolean isActive, Long idOwner);

    List<DishResponse> getRestaurantDishes(Long idRestaurant, int nElements, String category);

}
