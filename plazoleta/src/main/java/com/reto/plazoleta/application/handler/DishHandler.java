package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.dish.DishRequestDto;
import com.reto.plazoleta.application.dto.request.dish.DishUpdateRequestDto;
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
        dish.setActive(true);
        dish.setIdRestaurant(5L); // esto se cambia al agregar autenticacion
        dishServicePort.saveDish(dish);
    }

    @Override
    public void updateDish(DishUpdateRequestDto dishUpdateRequestDto) {
        DishModel dish = dishServicePort.getDish(dishUpdateRequestDto.getId());
        if (dish != null) {
            int newPrice = dishUpdateRequestDto.getPrice();
            String newDescription = dishUpdateRequestDto.getDescription();

            if (newPrice > 0) {
                dish.setPrice(newPrice);
            }
            if (newDescription != null) {
                dish.setDescription(newDescription);
            }
            dishServicePort.updateDish(dish);
        }
    }
}
