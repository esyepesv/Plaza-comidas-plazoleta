package com.reto.plazoleta.domain.usecase;

import com.reto.plazoleta.domain.api.IRestaurantServicePort;
import com.reto.plazoleta.domain.model.RestaurantModel;
import com.reto.plazoleta.domain.spi.IRestaurantPersistencePort;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(RestaurantModel restaurant) {
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public RestaurantModel getRestaurant(Long id) {
        return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public RestaurantModel getRestaurantByIdOwner(Long idOwner) {
        return restaurantPersistencePort.getRestaurantByIdOwner(idOwner);
    }
}