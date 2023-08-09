package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.dish.DishRequestDto;
import com.reto.plazoleta.application.dto.request.dish.DishUpdateRequestDto;
import com.reto.plazoleta.application.mapper.IDishRequestMapper;
import com.reto.plazoleta.domain.api.IDishServicePort;
import com.reto.plazoleta.domain.model.DishModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

    @Test
    void updateDish() {
        DishUpdateRequestDto dishUpdateRequestDto = new DishUpdateRequestDto();
        dishUpdateRequestDto.setId(1L);
        dishUpdateRequestDto.setPrice(100);
        dishUpdateRequestDto.setDescription("Una nueva descripción");

        DishModel dishModel = new DishModel();
        when(dishServicePort.getDish(dishUpdateRequestDto.getId())).thenReturn(dishModel);

        DishModel dish = dishServicePort.getDish(dishUpdateRequestDto.getId());

        assertEquals(0, dish.getPrice());
        assertNull(dish.getDescription());

        dishHandler.updateDish(dishUpdateRequestDto);

        assertEquals(100, dish.getPrice());
        assertEquals("Una nueva descripción", dish.getDescription());

        verify(dishServicePort).updateDish(dish);
    }

}