package com.reto.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDishModel {

    private Long id;
    private Long orderId;
    private Long idDish;
    private Integer number;
}
