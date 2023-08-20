package com.reto.plazoleta.application.dto.response.order;

import com.reto.plazoleta.domain.model.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;
    private Long idClient;
    private Long IdChef;
    private Date date;
    private State state;
    private List<OrderDishResponse> orderDishes;
}
