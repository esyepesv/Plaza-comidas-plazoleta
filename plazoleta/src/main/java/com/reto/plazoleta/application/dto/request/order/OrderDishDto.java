package com.reto.plazoleta.application.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDishDto {
    private Long idDish;
    private Integer number;
}
