package com.reto.plazoleta.domain.usecase;

import com.reto.plazoleta.domain.api.IOrderDishServicePort;
import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.spi.IOrderDishPersistencePort;

import java.util.List;

public class OrderDishUseCase implements IOrderDishServicePort {

    private final IOrderDishPersistencePort orderDishPersistencePort;

    public OrderDishUseCase(IOrderDishPersistencePort orderDishPersistencePort) {
        this.orderDishPersistencePort = orderDishPersistencePort;
    }

    @Override
    public void saveOrderDish(OrderDishModel orderDish) {
        orderDishPersistencePort.saveOrderDish(orderDish);
    }

    @Override
    public void saveOrderDish(List<OrderDishModel> orderDish) {
        orderDishPersistencePort.saveOrderDish(orderDish);
    }
}
