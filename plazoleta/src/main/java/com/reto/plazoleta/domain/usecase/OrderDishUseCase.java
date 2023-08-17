package com.reto.plazoleta.domain.usecase;

import com.reto.plazoleta.domain.api.IOrderDishServicePort;
import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.spi.IOrderDishPersistencePort;

public class OrderDishUseCase implements IOrderDishServicePort {

    private final IOrderDishPersistencePort orderDishPersistencePort;

    public OrderDishUseCase(IOrderDishPersistencePort orderDishPersistencePort) {
        this.orderDishPersistencePort = orderDishPersistencePort;
    }

    @Override
    public OrderDishModel saveOrderDish(OrderDishModel orderDish) {
        return orderDishPersistencePort.saveOrderDish(orderDish);
    }
}
