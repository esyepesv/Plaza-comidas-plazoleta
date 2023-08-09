package com.reto.plazoleta.application.mapper;

import com.reto.plazoleta.application.dto.request.restaurant.RestaurantRequestDto;
import com.reto.plazoleta.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantRequestMapper {
    RestaurantModel toRestaurant(RestaurantRequestDto restaurantRequestDto);
}
