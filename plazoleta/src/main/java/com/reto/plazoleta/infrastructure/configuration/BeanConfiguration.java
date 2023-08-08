package com.reto.plazoleta.infrastructure.configuration;

import com.reto.plazoleta.domain.api.IDishServicePort;
import com.reto.plazoleta.domain.api.IRestaurantServicePort;
import com.reto.plazoleta.domain.spi.IDishPersistencePort;
import com.reto.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.reto.plazoleta.domain.usecase.DishUseCase;
import com.reto.plazoleta.domain.usecase.RestaurantUseCase;
import com.reto.plazoleta.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.reto.plazoleta.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IDishRepository;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort());
    }
}