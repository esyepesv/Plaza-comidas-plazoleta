package com.reto.plazoleta.infrastructure.out.jpa.adapter;

import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.spi.IOrderDishPersistencePort;
import com.reto.plazoleta.infrastructure.out.jpa.entity.OrderDishEntity;
import com.reto.plazoleta.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.reto.plazoleta.infrastructure.out.jpa.repository.IOrderDishRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderDishJpaAdapter implements IOrderDishPersistencePort {

    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    @Override
    public void saveOrderDish(List<OrderDishModel> orderDish) {
        List<OrderDishEntity> orderDishEntity = orderDishEntityMapper.toOrderDishEntityList(orderDish);
        orderDishRepository.saveAll(orderDishEntity);
    }

    @Override
    public void saveOrderDish(OrderDishModel orderDish) {

    }

}
