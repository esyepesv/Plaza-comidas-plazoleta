package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.UserDto;
import com.reto.plazoleta.application.dto.request.restaurant.RestaurantRequestDto;
import com.reto.plazoleta.application.dto.response.RestaurantResponse;
import com.reto.plazoleta.application.exception.InvalidUserRoleException;
import com.reto.plazoleta.application.exception.UserNotFoundException;
import com.reto.plazoleta.application.mapper.IRestaurantRequestMapper;
import com.reto.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.reto.plazoleta.domain.api.IRestaurantServicePort;
import com.reto.plazoleta.domain.model.RestaurantModel;
import com.reto.plazoleta.infrastructure.out.feign.UserFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantHandlerTest {

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @Mock
    private IRestaurantRequestMapper restaurantRequestMapper;

    @Mock
    private IRestaurantResponseMapper restaurantResponseMapper;

    @Mock
    private UserFeignClient userFeignClient;

    @InjectMocks
    private RestaurantHandler restaurantHandler;

    @Test
    void testSaveRestaurant() {
        // Given
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();
        restaurantRequestDto.setName("My Restaurant");
        restaurantRequestDto.setDirection("123 Main Street");
        restaurantRequestDto.setIdOwner(1L);
        restaurantRequestDto.setPhone("+1234567890");
        restaurantRequestDto.setUrlLogo("https://example.com/logo.png");
        restaurantRequestDto.setNit("123456789");

        UserDto user = new UserDto();
        user.setIdRol(2L);
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);

        when(userFeignClient.getUser(1L)).thenReturn(responseEntity);

        RestaurantModel expectedRestaurantModel = new RestaurantModel();

        when(restaurantRequestMapper.toRestaurant(restaurantRequestDto)).thenReturn(expectedRestaurantModel);

        restaurantHandler.saveRestaurant(restaurantRequestDto);

        verify(restaurantServicePort).saveRestaurant(expectedRestaurantModel);
    }

    @Test
    void testSaveRestaurantInvalidUser() {
        // Given
        RestaurantRequestDto requestDto = new RestaurantRequestDto();
        requestDto.setIdOwner(1L);

        UserDto userDto = new UserDto();
        userDto.setIdRol(1L); // This is an invalid role for the test

        when(userFeignClient.getUser(1L)).thenReturn(ResponseEntity.ok(userDto));

        assertThrows(InvalidUserRoleException.class, () -> {
            restaurantHandler.saveRestaurant(requestDto);
        });
    }



    @Test
    void testSaveRestaurantUserNotFound() {
        RestaurantRequestDto requestDto = new RestaurantRequestDto();
        requestDto.setIdOwner(1L);

        when(userFeignClient.getUser(1L)).thenReturn(ResponseEntity.notFound().build());

        assertThrows(UserNotFoundException.class, () -> {
            restaurantHandler.saveRestaurant(requestDto);
        });
    }

    @Test
    void getAllRestaurants() {
        int nElements = 5;

        List<RestaurantModel> restaurantModels = new ArrayList<>();
        for (int i = 1; i <= nElements; i++) {
            RestaurantModel restaurantModel = new RestaurantModel();
            restaurantModel.setName("Restaurant " + i);
            restaurantModels.add(restaurantModel);
        }

        when(restaurantServicePort.getAllRestaurants()).thenReturn(restaurantModels);

        List<RestaurantResponse> expectedResponses = new ArrayList<>();
        for (int i = 1; i <= nElements; i++) {
            RestaurantResponse response = new RestaurantResponse();
            response.setName("Restaurant " + i);
            expectedResponses.add(response);
        }

        when(restaurantResponseMapper.toResposeList(restaurantModels)).thenReturn(expectedResponses);

        List<RestaurantResponse> actualResponses = restaurantHandler.getAllRestaurants(nElements);

        assertEquals(nElements, actualResponses.size());
        assertEquals(expectedResponses, actualResponses);
    }
}