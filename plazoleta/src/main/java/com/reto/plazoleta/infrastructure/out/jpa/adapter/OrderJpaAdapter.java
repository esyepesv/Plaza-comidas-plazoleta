package com.reto.plazoleta.infrastructure.out.jpa.adapter;

import com.reto.plazoleta.domain.model.OrderModel;
import com.reto.plazoleta.domain.spi.IOrderPersistencePort;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public OrderModel saveOrder(OrderModel order) {
        return orderEntityMapper.toOrder(orderRepository.save(orderEntityMapper.toEntity(order)));
    }
}
