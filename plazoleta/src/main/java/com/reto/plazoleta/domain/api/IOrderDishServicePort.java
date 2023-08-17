package com.reto.plazoleta.domain.api;

import com.reto.plazoleta.domain.model.OrderDishModel;

public interface IOrderDishServicePort {
    OrderDishModel saveOrderDish(OrderDishModel orderDish);
}
