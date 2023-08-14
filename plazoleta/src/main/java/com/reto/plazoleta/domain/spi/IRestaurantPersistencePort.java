package com.reto.plazoleta.domain.spi;

import com.reto.plazoleta.domain.model.RestaurantModel;

public interface IRestaurantPersistencePort {
    void saveRestaurant(RestaurantModel restaurant);
    RestaurantModel getRestaurant(Long id);
    RestaurantModel getRestaurantByIdOwner(Long idOwner);


}