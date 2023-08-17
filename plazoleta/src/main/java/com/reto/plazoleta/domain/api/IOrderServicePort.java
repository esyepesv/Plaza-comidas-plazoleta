package com.reto.plazoleta.domain.api;


import com.reto.plazoleta.domain.model.OrderModel;

public interface IOrderServicePort {
    void saveOrder(OrderModel order);

}
