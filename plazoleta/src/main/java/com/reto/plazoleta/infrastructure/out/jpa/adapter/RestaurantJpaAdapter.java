package com.reto.plazoleta.infrastructure.out.jpa.adapter;

import com.reto.plazoleta.domain.model.RestaurantModel;
import com.reto.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.reto.plazoleta.infrastructure.exception.NoDataFoundException;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveRestaurant(RestaurantModel restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public RestaurantModel getRestaurant(Long id) {

        return restaurantEntityMapper.toRestaurant(restaurantRepository.findById(id).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public RestaurantModel getRestaurantByIdOwner(Long idOwner) {
        return restaurantEntityMapper.toRestaurant(restaurantRepository.findByIdOwner(idOwner).orElseThrow(NoDataFoundException::new));
    }



}