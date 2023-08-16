package com.reto.plazoleta.domain.api;

import com.reto.plazoleta.domain.model.RestaurantModel;

import java.util.List;


public interface IRestaurantServicePort {

    void saveRestaurant(RestaurantModel restaurantModel);

    RestaurantModel getRestaurant(Long id);

    RestaurantModel getRestaurantByIdOwner(Long idOwner);

    List<RestaurantModel> getAllRestaurants();


}