package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.restaurant.RestaurantRequestDto;

public interface IRestaurantHandler {

    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);

}