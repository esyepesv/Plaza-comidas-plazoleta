package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.DishRequestDto;
import com.reto.plazoleta.application.mapper.IDishRequestMapper;
import com.reto.plazoleta.domain.api.IDishServicePort;
import com.reto.plazoleta.domain.model.DishModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler{

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        DishModel dish = dishRequestMapper.toDish(dishRequestDto);
        dishServicePort.saveDish(dish);
    }
}
