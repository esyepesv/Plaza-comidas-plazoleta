package com.reto.plazoleta.domain.usecase;

import com.reto.plazoleta.domain.api.IOrderServicePort;
import com.reto.plazoleta.domain.model.OrderModel;
import com.reto.plazoleta.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public OrderModel saveOrder(OrderModel order) {
        return orderPersistencePort.saveOrder(order);
    }

    @Override
    public List<OrderModel> getRestaurantOrders(Long idRestaurant) {
        return orderPersistencePort.getRestaurantOrders(idRestaurant);
    }
}
