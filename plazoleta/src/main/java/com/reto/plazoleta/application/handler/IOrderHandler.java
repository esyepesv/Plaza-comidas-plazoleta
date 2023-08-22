package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.order.OrderDto;
import com.reto.plazoleta.application.dto.response.order.OrderResponse;
import com.reto.plazoleta.domain.model.State;

import java.util.List;

public interface IOrderHandler {
    void saveOrder (OrderDto orderDto);

    List<OrderResponse> getRestaurantOrders(Long idRestaurant, int nElements, State state);

    void takeOrder(Long idOrder, Long idEmployee);

    void markAsReady(Long idOrder);

    void deliver(Long idOrder, int pin);

    String cancelOrder(Long idClient);
}
