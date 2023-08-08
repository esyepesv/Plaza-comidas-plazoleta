package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.DishRequestDto;

public interface IDishHandler {
    void saveDish(DishRequestDto dishRequestDto);
}
