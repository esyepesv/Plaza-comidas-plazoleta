package com.reto.plazoleta.application.mapper;

import com.reto.plazoleta.application.dto.request.order.OrderDishDto;
import com.reto.plazoleta.application.dto.request.order.OrderDto;
import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {

    OrderModel toOrder(OrderDto orderDto);

    List<OrderDishModel> toOrderDishList(List<OrderDishDto> orderDishes);

}
