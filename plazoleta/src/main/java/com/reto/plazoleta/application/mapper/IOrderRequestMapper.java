package com.reto.plazoleta.application.mapper;

import com.reto.plazoleta.application.dto.request.order.OrderDto;
import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {

    OrderModel toOrder(OrderDto orderDto);

  /*  @Mapping(source = "orderDto.orderDishes.idDish", target = "idDish")
    @Mapping(source = "orderDto.orderDishes.number", target = "number")
    OrderDishModel toOrderDish(OrderDto orderDto);
*/
}
