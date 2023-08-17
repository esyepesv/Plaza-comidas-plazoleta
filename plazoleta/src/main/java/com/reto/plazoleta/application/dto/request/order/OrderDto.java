package com.reto.plazoleta.application.dto.request.order;

import com.reto.plazoleta.domain.model.State;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long idRestaurant;
    private Long idClient;
    private List<OrderDishDto> orderDishes;
    private State state;
}
