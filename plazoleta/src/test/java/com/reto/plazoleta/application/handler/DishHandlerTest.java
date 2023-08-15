package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.dish.DishRequestDto;
import com.reto.plazoleta.application.dto.request.dish.DishUpdateRequestDto;
import com.reto.plazoleta.application.mapper.IDishRequestMapper;
import com.reto.plazoleta.domain.api.IDishServicePort;
import com.reto.plazoleta.domain.api.IRestaurantServicePort;
import com.reto.plazoleta.domain.model.DishModel;
import com.reto.plazoleta.domain.model.RestaurantModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DishHandlerTest {

    @Mock
    private IDishServicePort dishServicePort;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

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

        dishHandler.saveDish(dishRequestDto, 16L);

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

        dishHandler.updateDish(dishUpdateRequestDto, 16L);

        assertEquals(100, dish.getPrice());
        assertEquals("Una nueva descripción", dish.getDescription());

        verify(dishServicePort).updateDish(dish);
    }

    @Test
    void enableDish() {
        Long id = 1L;
        boolean isActive = true;
        Long idOwner = 2L;
        DishModel dishModel = new DishModel();
        dishModel.setId(id);
        dishModel.setActive(false);
        dishModel.setIdRestaurant(idOwner);
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(idOwner);

        when(dishServicePort.getDish(id)).thenReturn(dishModel);
        when(restaurantServicePort.getRestaurantByIdOwner(idOwner)).thenReturn(restaurantModel);

        dishHandler.enableDish(id, isActive, idOwner);

        assertTrue(dishModel.isActive());
        verify(dishServicePort).updateDish(dishModel);
    }
}