package com.reto.plazoleta.infrastructure.out.jpa.mapper;

import com.reto.plazoleta.domain.model.RestaurantModel;
import com.reto.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantEntityMapper {

    RestaurantEntity toEntity(RestaurantModel restaurantModel);
    RestaurantModel toRestaurantModel(Optional<RestaurantEntity> restaurantEntity);

}