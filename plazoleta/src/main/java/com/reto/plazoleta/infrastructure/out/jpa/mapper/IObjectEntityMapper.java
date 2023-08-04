package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.infrastructure.out.jpa.entity.ObjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IObjectEntityMapper {

    ObjectEntity toEntity(com.pragma.powerup.domain.model.RestaurantModel user);
    com.pragma.powerup.domain.model.RestaurantModel toObjectModel(ObjectEntity objectEntity);
    List<com.pragma.powerup.domain.model.RestaurantModel> toObjectModelList(List<ObjectEntity> userEntityList);
}