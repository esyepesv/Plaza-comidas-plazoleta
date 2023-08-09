package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.DishRequestDto;
import com.reto.plazoleta.application.mapper.IDishRequestMapper;
import com.reto.plazoleta.domain.api.IDishServicePort;
import com.reto.plazoleta.domain.model.DishModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DishHandlerTest {

    @Mock
    private IDishServicePort dishServicePort;

    @Mock
    private IDishRequestMapper dishRequestMapper;

    @InjectMocks
    private DishHandler dishHandler;

    @Test
    void saveDish() {
        DishRequestDto dishRequestDto = new DishRequestDto();
        dishRequestDto.setName("Pizza");
        dishRequestDto.setDescription("Una deliciosa pizza");
        dishRequestDto.setPrice(10);
        dishRequestDto.setUrlImage("https://www.pizza.com/img/pizza.png");
        dishRequestDto.setCategory("Italiana");


        DishModel dishModel = new DishModel();

        when(dishRequestMapper.toDish(dishRequestDto)).thenReturn(dishModel);

        DishModel dish = dishRequestMapper.toDish(dishRequestDto);
        dish.setActive(true);
        dish.setIdRestaurant(5L);

        dishHandler.saveDish(dishRequestDto);

        verify(dishServicePort).saveDish(dish);
    }
}