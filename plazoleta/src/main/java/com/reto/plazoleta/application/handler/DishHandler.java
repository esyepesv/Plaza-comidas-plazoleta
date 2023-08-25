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

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler{

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;
    private final IRestaurantServicePort restaurantServicePort;


    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        DishModel dish = dishRequestMapper.toDish(dishRequestDto);
        dish.setActive(true);
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

    @Override
    public void enableDish(Long id, boolean isActive) {
        DishModel dish = dishServicePort.getDish(id);

        if (dish != null) {
            dish.setActive(isActive);
            dishServicePort.updateDish(dish);
        }
    }

    @Override
    public List<DishResponse> getRestaurantDishes(Long idRestaurant, int nElements, String category) {
        List<DishModel> dishModels = dishServicePort.getDishes();

        /*if (!category.isBlank()) {
            dishModels = dishModels.stream()
                    .filter(dish -> category.equalsIgnoreCase(dish.getCategory()))
                    .toList();
        }

        dishModels = dishModels.stream()
                .filter(DishModel::isActive)
                .sorted(Comparator.comparing(DishModel::getName))
                .toList();

        if (nElements > 0 && nElements < dishModels.size()) {
            dishModels = dishModels.subList(0, nElements);
        }*/

        return dishResponseMapper.toResponseList(dishModels);
    }


}
