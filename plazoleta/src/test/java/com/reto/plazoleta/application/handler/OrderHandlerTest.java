package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.request.order.OrderDishDto;
import com.reto.plazoleta.application.dto.request.order.OrderDto;
import com.reto.plazoleta.application.dto.response.order.OrderResponse;
import com.reto.plazoleta.application.mapper.IOrderRequestMapper;
import com.reto.plazoleta.application.mapper.IOrderResponseMapper;
import com.reto.plazoleta.domain.api.IOrderDishServicePort;
import com.reto.plazoleta.domain.api.IOrderServicePort;
import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.model.OrderModel;
import com.reto.plazoleta.domain.model.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
class OrderHandlerTest {

    @Mock
    private IOrderServicePort orderServicePort;

    @Mock
    private IOrderDishServicePort orderDishServicePort;

    @Mock
    private IOrderRequestMapper orderRequestMapper;

    @Mock
    private IOrderResponseMapper orderResponseMapper;

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
        orderDishes.add(new OrderDishModel(null, null, 34L, null, 2));
        orderDishes.add(new OrderDishModel(null, null, 35L, null,  2));

        when(orderRequestMapper.toOrder(orderDto)).thenReturn(order);
        when(orderRequestMapper.toOrderDishList(orderDto.getOrderDishes())).thenReturn(orderDishes);


        when(orderServicePort.saveOrder(order)).thenReturn(order);

        orderHandler.saveOrder(orderDto);

        verify(orderServicePort).saveOrder(order);
        verify(orderDishServicePort).saveOrderDish(orderDishes);
    }

    @Test
    void getRestaurantOrders() {
        Long idRestaurant = 1L;
        int nElements = 2;
        State state = State.PENDIENTE;

        List<OrderModel> mockOrders = new ArrayList<>();
        mockOrders.add(new OrderModel(1L,idRestaurant,null, null, null, State.PENDIENTE));
        mockOrders.add(new OrderModel(2L,idRestaurant,null, null, null, State.PENDIENTE));

        when(orderServicePort.getRestaurantOrders(idRestaurant)).thenReturn(mockOrders);

        List<OrderDishModel> mockOrderDishes = new ArrayList<>();
        mockOrderDishes.add(new OrderDishModel(null, 1L, null, "Dish 1", 1));
        mockOrderDishes.add(new OrderDishModel(null, 2L, null, "Dish 2", 1));

        when(orderDishServicePort.getOrderDishes(anyLong())).thenReturn(mockOrderDishes);

        List<OrderResponse> mockResponse = new ArrayList<>();
        mockResponse.add(new OrderResponse(1L, null ,null, null, State.PENDIENTE,null));
        mockResponse.add(new OrderResponse(2L, null ,null, null, State.PENDIENTE,null));

        when(orderResponseMapper.toOrderResponse(mockOrders)).thenReturn(mockResponse);

        List<OrderResponse> result = orderHandler.getRestaurantOrders(idRestaurant, nElements, state);


        assertEquals(nElements, result.size());
        for (OrderResponse response : result) {
            assertEquals(state, response.getState());
        }

    }
}