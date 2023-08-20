package com.reto.plazoleta.application.mapper;

import com.reto.plazoleta.application.dto.response.order.OrderDishResponse;
import com.reto.plazoleta.application.dto.response.order.OrderResponse;
import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {

    default List<OrderResponse> toOrderResponse(List<OrderModel> orderList){
        return orderList.stream()
                .map(order -> {
                    OrderResponse response = new OrderResponse();
                    response.setId(order.getId());
                    response.setIdClient(order.getIdClient());
                    response.setIdChef(order.getIdChef());
                    response.setDate(order.getDate());
                    response.setState(order.getState());
                    return response;
                }).toList();
    }
    List<OrderDishResponse> toOrderDishResponse(List<OrderDishModel> orderDishes);
}
