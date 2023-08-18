package com.reto.plazoleta.infrastructure.out.jpa.mapper;

import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.model.OrderModel;
import com.reto.plazoleta.infrastructure.out.jpa.entity.OrderDishEntity;
import com.reto.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderDishEntityMapper {
    OrderDishEntity toEntity(OrderDishModel orderDishModel);
    OrderDishModel toOrderDish(OrderDishEntity orderDishEntity);
    List<OrderDishModel> toOrderDishList(List<OrderDishEntity> orderDishEntityList);
    List<OrderDishEntity> toOrderDishEntityList(List<OrderDishModel> orderDishModelList);
}
