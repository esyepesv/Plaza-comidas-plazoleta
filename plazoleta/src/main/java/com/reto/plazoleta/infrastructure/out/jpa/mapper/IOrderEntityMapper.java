package com.reto.plazoleta.infrastructure.out.jpa.mapper;

import com.reto.plazoleta.domain.model.OrderModel;
import com.reto.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderEntityMapper {
    OrderEntity toEntity(OrderModel orderModel);
    OrderModel toOrder(OrderEntity orderEntity);
    List<OrderModel> toOrderList(List<OrderEntity> orderEntityList);
}
