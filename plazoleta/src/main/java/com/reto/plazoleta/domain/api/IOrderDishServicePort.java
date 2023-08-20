package com.reto.plazoleta.domain.api;

import com.reto.plazoleta.domain.model.OrderDishModel;

import java.util.List;

public interface IOrderDishServicePort {
    void saveOrderDish(OrderDishModel orderDish);
    void saveOrderDish(List<OrderDishModel> orderDish);

    List<OrderDishModel> getOrderDishes(Long idOrder);
}
