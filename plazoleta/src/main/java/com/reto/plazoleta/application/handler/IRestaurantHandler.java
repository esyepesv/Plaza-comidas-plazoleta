package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.restaurant.RestaurantRequestDto;
import com.reto.plazoleta.application.dto.response.RestaurantResponse;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);

    List<RestaurantResponse> getAllRestaurants(int nElements);
}