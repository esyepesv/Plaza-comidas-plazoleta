package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.order.OrderDto;

public interface IOrderHandler {
    void saveOrder (OrderDto orderDto);
}
