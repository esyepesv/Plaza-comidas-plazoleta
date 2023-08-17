package com.reto.plazoleta.domain.spi;

import com.reto.plazoleta.domain.model.OrderDishModel;

public interface IOrderDishPersistencePort {
    OrderDishModel saveOrderDish(OrderDishModel orderDish);

}
