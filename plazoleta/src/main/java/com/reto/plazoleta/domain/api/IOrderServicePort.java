package com.reto.plazoleta.domain.api;


import com.reto.plazoleta.domain.model.OrderModel;

import java.util.List;

public interface IOrderServicePort {
    OrderModel saveOrder(OrderModel order);

    List<OrderModel> getRestaurantOrders(Long idRestaurant);

}
