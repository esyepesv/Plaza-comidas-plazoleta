package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.RestaurantRequestDto;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);

}