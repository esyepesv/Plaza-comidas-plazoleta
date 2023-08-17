package com.reto.plazoleta.domain.spi;

import com.reto.plazoleta.domain.model.OrderModel;

public interface IOrderPersistencePort {
    void saveOrder(OrderModel order);
}
