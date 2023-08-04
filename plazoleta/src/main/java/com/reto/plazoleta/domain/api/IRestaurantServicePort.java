package com.reto.plazoleta.domain.api;

import com.reto.plazoleta.domain.model.RestaurantModel;


public interface IRestaurantServicePort {

    void saveRestaurant(RestaurantModel restaurantModel);

}