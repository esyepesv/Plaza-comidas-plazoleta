package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.dish.DishRequestDto;
import com.reto.plazoleta.application.dto.request.dish.DishUpdateRequestDto;
import com.reto.plazoleta.application.mapper.IDishRequestMapper;
import com.reto.plazoleta.domain.api.IDishServicePort;
import com.reto.plazoleta.domain.model.DishModel;
import com.reto.plazoleta.domain.model.RestaurantModel;
import com.reto.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler{

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto, Long idOwner) {
        DishModel dish = dishRequestMapper.toDish(dishRequestDto);
        dish.setActive(true);
        RestaurantEntity restaurantEntity = restaurantRepository.findByIdOwner(idOwner);
        RestaurantModel restaurantModel = restaurantEntityMapper.toRestaurantModel(restaurantEntity);
        Long idRestaurant = restaurantModel.getId();
        dish.setIdRestaurant(idRestaurant);
        dishServicePort.saveDish(dish);
    }

    @Override
    public void updateDish(DishUpdateRequestDto dishUpdateRequestDto) {
        DishModel dish = dishServicePort.getDish(dishUpdateRequestDto.getId());
        if (dish != null) {
            int newPrice = dishUpdateRequestDto.getPrice();
            String newDescription = dishUpdateRequestDto.getDescription();

            if (newPrice > 0) {
                dish.setPrice(newPrice);
            }
            if (newDescription != null) {
                dish.setDescription(newDescription);
            }
            dishServicePort.updateDish(dish);
        }
    }
}
