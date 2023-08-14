package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.dish.DishRequestDto;
import com.reto.plazoleta.application.dto.request.dish.DishUpdateRequestDto;

public interface IDishHandler {
    void saveDish(DishRequestDto dishRequestDto, Long idOwner);

    void updateDish(DishUpdateRequestDto dishUpdateRequestDto, Long idOwner);

    void enableDish(Long id, boolean isActive, Long idOwner);
}
