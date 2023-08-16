package com.reto.plazoleta.domain.spi;

import com.reto.plazoleta.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {
    void saveRestaurant(RestaurantModel restaurant);
    RestaurantModel getRestaurant(Long id);
    RestaurantModel getRestaurantByIdOwner(Long idOwner);

    List<RestaurantModel> getAllRestaurants();


}