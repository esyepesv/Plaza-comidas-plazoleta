package com.reto.plazoleta.domain.spi;

import com.reto.plazoleta.domain.model.OrderModel;

import java.util.List;

public interface IOrderPersistencePort {
    OrderModel saveOrder(OrderModel order);
    List<OrderModel> getRestaurantOrders(Long idRestaurant);
    OrderModel getOrder(Long idOrder);
    void updateOrder(OrderModel order);
    OrderModel getOrderByIdClient(Long idClient);

}
