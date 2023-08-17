package com.reto.plazoleta.domain.model;

import com.reto.plazoleta.application.dto.request.order.OrderDishDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderModel {

    private Long id;
    private Long idRestaurant;
    private Long idClient;
    private Long idChef;
    private Date date;
    private State state;

}
