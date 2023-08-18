package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.order.OrderDishDto;
import com.reto.plazoleta.application.dto.request.order.OrderDto;
import com.reto.plazoleta.application.mapper.IOrderRequestMapper;
import com.reto.plazoleta.domain.api.IOrderDishServicePort;
import com.reto.plazoleta.domain.api.IOrderServicePort;
import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler{

    private final IOrderServicePort orderServicePort;
    private final IOrderDishServicePort orderDishServicePort;
    private final IOrderRequestMapper orderRequestMapper;

    @Override
    public void saveOrder(OrderDto orderDto) {
        OrderModel order = orderRequestMapper.toOrder(orderDto);
        List<OrderDishModel> orderDishes= orderRequestMapper.toOrderDishList(orderDto.getOrderDishes());
        order = orderServicePort.saveOrder(order);
        for(OrderDishModel orderDish: orderDishes) orderDish.setOrderId(order.getId());
        orderDishServicePort.saveOrderDish(orderDishes);
    }
}
