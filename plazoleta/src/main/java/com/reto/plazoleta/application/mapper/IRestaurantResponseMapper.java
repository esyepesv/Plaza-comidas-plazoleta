package com.reto.plazoleta.application.mapper;

import com.reto.plazoleta.application.dto.response.RestaurantResponse;
import com.reto.plazoleta.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {

    default List<RestaurantResponse> toResposeList(List<RestaurantModel> restaurantLis){
        return restaurantLis.stream()
                .map(restaurant ->{
                    RestaurantResponse response = new RestaurantResponse();
                    response.setName(restaurant.getName());
                    response.setUrlLogo(restaurant.getUrlLogo());
                    return response;
                        }
                ).toList();
    }
}

