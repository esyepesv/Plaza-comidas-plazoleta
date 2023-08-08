package com.reto.plazoleta.infrastructure.out.jpa.mapper;

import com.reto.plazoleta.domain.model.DishModel;
import com.reto.plazoleta.infrastructure.out.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IDishEntityMapper {
    DishEntity toEntity(DishModel dishModel);
    DishModel toDish(DishEntity dishEntity);
}
