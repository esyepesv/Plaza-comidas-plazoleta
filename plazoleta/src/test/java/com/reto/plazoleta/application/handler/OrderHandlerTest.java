package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.order.OrderDishDto;
import com.reto.plazoleta.application.dto.request.order.OrderDto;
import com.reto.plazoleta.application.mapper.IOrderRequestMapper;
import com.reto.plazoleta.domain.api.IOrderDishServicePort;
import com.reto.plazoleta.domain.api.IOrderServicePort;
import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.model.OrderModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderHandlerTest {

    @Mock
    private IOrderServicePort orderServicePort;

    @Mock
    private IOrderDishServicePort orderDishServicePort;

    @Mock
    private IOrderRequestMapper orderRequestMapper;

    @InjectMocks
    private OrderHandler orderHandler;

    @Test
    void saveOrder() {
        OrderDto orderDto = new OrderDto();
        orderDto.setIdRestaurant(19L);
        orderDto.setIdClient(2L);
        orderDto.setOrderDishes(Arrays.asList(new OrderDishDto(34L, 2), new OrderDishDto(35L, 2)));

        OrderModel order = new OrderModel();
        order.setIdRestaurant(19L);
        order.setIdClient(2L);

        List<OrderDishModel> orderDishes = new ArrayList<>();
        orderDishes.add(new OrderDishModel(null, null, 34L, 2));
        orderDishes.add(new OrderDishModel(null, null, 35L, 2));

        when(orderRequestMapper.toOrder(orderDto)).thenReturn(order);
        when(orderRequestMapper.toOrderDishList(orderDto.getOrderDishes())).thenReturn(orderDishes);


        when(orderServicePort.saveOrder(order)).thenReturn(order);

        orderHandler.saveOrder(orderDto);

        verify(orderServicePort).saveOrder(order);
        verify(orderDishServicePort).saveOrderDish(orderDishes);
    }

}