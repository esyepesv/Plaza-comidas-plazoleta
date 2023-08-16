package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.dish.DishRequestDto;
import com.reto.plazoleta.application.dto.request.dish.DishUpdateRequestDto;
import com.reto.plazoleta.application.dto.response.DishResponse;
import com.reto.plazoleta.application.mapper.IDishRequestMapper;
import com.reto.plazoleta.application.mapper.IDishResponseMapper;
import com.reto.plazoleta.domain.api.IDishServicePort;
import com.reto.plazoleta.domain.api.IRestaurantServicePort;
import com.reto.plazoleta.domain.model.DishModel;
import com.reto.plazoleta.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler{

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;
    private final IRestaurantServicePort restaurantServicePort;


    @Override
    public void saveDish(DishRequestDto dishRequestDto, Long idOwner) {
        DishModel dish = dishRequestMapper.toDish(dishRequestDto);
        dish.setActive(true);

        RestaurantModel restaurant = restaurantServicePort.getRestaurantByIdOwner(idOwner);
        //System.out.println(restaurant2.toString());

        Long idRestaurant = restaurant.getId();
        dish.setIdRestaurant(idRestaurant);
        dishServicePort.saveDish(dish);
    }

    @Override
    public void updateDish(DishUpdateRequestDto dishUpdateRequestDto, Long idOwner) {

        DishModel dish = dishServicePort.getDish(dishUpdateRequestDto.getId());
        RestaurantModel restaurantModel = restaurantServicePort.getRestaurantByIdOwner(idOwner);
        Long idRestaurant = restaurantModel.getId();


        if (dish != null && (Objects.equals(dish.getIdRestaurant(), idRestaurant))) {
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

    @Override
    public void enableDish(Long id, boolean isActive, Long idOwner) {
        DishModel dish = dishServicePort.getDish(id);
        RestaurantModel restaurantModel = restaurantServicePort.getRestaurantByIdOwner(idOwner);
        Long idRestaurant = restaurantModel.getId();

        if (dish != null && (Objects.equals(dish.getIdRestaurant(), idRestaurant))) {
            dish.setActive(isActive);
            dishServicePort.updateDish(dish);
        }
    }

    @Override
    public List<DishResponse> getRestaurantDishes(Long idRestaurant, int nElements, String category) {
        List<DishModel> dishModels = dishServicePort.getRestaurantDishes(idRestaurant);

        // Filter dishes by category if provided
        if (!category.isBlank()) {
            dishModels = dishModels.stream()
                    .filter(dish -> category.equalsIgnoreCase(dish.getCategory()))
                    .collect(Collectors.toList());
        }

        // Sort the list by name
        dishModels.sort(Comparator.comparing(DishModel::getName));

        // Limit the list to the specified number of elements
        if (nElements > 0 && nElements < dishModels.size()) {
            dishModels = dishModels.subList(0, nElements);
        }

        return dishResponseMapper.toResponseList(dishModels);
    }


}
