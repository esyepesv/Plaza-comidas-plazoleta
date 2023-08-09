package com.reto.plazoleta.application.handler;


import com.reto.plazoleta.application.dto.UserDto;
import com.reto.plazoleta.application.dto.request.restaurant.RestaurantRequestDto;
import com.reto.plazoleta.application.exception.InvalidUserRoleException;
import com.reto.plazoleta.application.exception.UserNotFoundException;
import com.reto.plazoleta.application.mapper.IRestaurantRequestMapper;
import com.reto.plazoleta.domain.api.IRestaurantServicePort;
import com.reto.plazoleta.domain.model.RestaurantModel;
import com.reto.plazoleta.infrastructure.out.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final UserFeignClient client;


    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        ResponseEntity<UserDto> response = client.getUser(restaurantRequestDto.getIdOwner());
        if(response.getStatusCode().is2xxSuccessful()){
            UserDto user = response.getBody();
            if (user != null && user.getIdRol() == 2) {
                RestaurantModel restaurantModel = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
                restaurantServicePort.saveRestaurant(restaurantModel);
            }
            else throw new InvalidUserRoleException();
        }
        else throw new UserNotFoundException();
    }
}