package com.reto.plazoleta.infrastructure.configuration;

import com.reto.plazoleta.domain.api.IDishServicePort;
import com.reto.plazoleta.domain.api.IOrderDishServicePort;
import com.reto.plazoleta.domain.api.IOrderServicePort;
import com.reto.plazoleta.domain.api.IRestaurantServicePort;
import com.reto.plazoleta.domain.spi.IDishPersistencePort;
import com.reto.plazoleta.domain.spi.IOrderDishPersistencePort;
import com.reto.plazoleta.domain.spi.IOrderPersistencePort;
import com.reto.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.reto.plazoleta.domain.usecase.DishUseCase;
import com.reto.plazoleta.domain.usecase.OrderDishUseCase;
import com.reto.plazoleta.domain.usecase.OrderUseCase;
import com.reto.plazoleta.domain.usecase.RestaurantUseCase;
import com.reto.plazoleta.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.reto.plazoleta.infrastructure.out.jpa.adapter.OrderDishJpaAdapter;
import com.reto.plazoleta.infrastructure.out.jpa.adapter.OrderJpaAdapter;
import com.reto.plazoleta.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IDishRepository;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IOrderDishRepository;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IOrderRepository;
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
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort());
    }

    @Bean
    public IOrderDishPersistencePort orderDishPersistencePort(){
        return new OrderDishJpaAdapter(orderDishRepository, orderDishEntityMapper);
    }

    @Bean
    public IOrderDishServicePort orderDishServicePort(){
        return new OrderDishUseCase(orderDishPersistencePort());
    }

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