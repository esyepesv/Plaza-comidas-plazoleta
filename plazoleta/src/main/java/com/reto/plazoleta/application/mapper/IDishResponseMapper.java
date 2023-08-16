package com.reto.plazoleta.application.mapper;

import com.reto.plazoleta.application.dto.response.DishResponse;
import com.reto.plazoleta.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {

    default List<DishResponse> toResponseList(List<DishModel> dishModelList){
        return dishModelList.stream()
                .map(dish -> {
                    DishResponse response = new DishResponse();
                    response.setName(dish.getName());
                    response.setPrice(dish.getPrice());
                    response.setDescription(dish.getDescription());
                    response.setUrlImage(dish.getUrlImage());
                    response.setCategory(dish.getCategory());
                    return response;
                        }
                ).toList();
    }

}
