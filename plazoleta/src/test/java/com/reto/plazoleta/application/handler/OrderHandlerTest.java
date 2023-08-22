package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.UserDto;
import com.reto.plazoleta.application.dto.request.SMSSendRequest;
import com.reto.plazoleta.application.dto.request.order.OrderDishDto;
import com.reto.plazoleta.application.dto.request.order.OrderDto;
import com.reto.plazoleta.application.dto.response.order.OrderResponse;
import com.reto.plazoleta.application.exception.InvalidPinException;
import com.reto.plazoleta.application.exception.InvalidStateException;
import com.reto.plazoleta.application.mapper.IOrderRequestMapper;
import com.reto.plazoleta.application.mapper.IOrderResponseMapper;
import com.reto.plazoleta.domain.api.IDishServicePort;
import com.reto.plazoleta.domain.api.IOrderDishServicePort;
import com.reto.plazoleta.domain.api.IOrderServicePort;
import com.reto.plazoleta.domain.model.DishModel;
import com.reto.plazoleta.domain.model.OrderDishModel;
import com.reto.plazoleta.domain.model.OrderModel;
import com.reto.plazoleta.domain.model.State;
import com.reto.plazoleta.infrastructure.out.feign.MessageFeignClient;
import com.reto.plazoleta.infrastructure.out.feign.UserFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OrderHandlerTest {

    @Mock
    private IOrderServicePort orderServicePort;

    @Mock
    private IDishServicePort dishServicePort;

    @Mock
    private IOrderDishServicePort orderDishServicePort;

    @Mock
    private IOrderRequestMapper orderRequestMapper;

    @Mock
    private IOrderResponseMapper orderResponseMapper;

    @Mock
    private UserFeignClient userFeignClient;

    @Mock
    private MessageFeignClient messageFeignClient;

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

        DishModel dish = new DishModel();
        dish.setName("dish");

        when(dishServicePort.getDish(anyLong())).thenReturn(dish);


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
        mockOrders.add(new OrderModel(1L,idRestaurant,null, null, null, State.PENDIENTE, 0));
        mockOrders.add(new OrderModel(2L,idRestaurant,null, null, null, State.PENDIENTE,0));

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

    @Test
    void takeOrder() {
        Long idOrder = 1L;
        Long idEmployee = 2L;

        OrderModel mockOrder = new OrderModel(1L, null, null, null, null, State.PENDIENTE,0);
        when(orderServicePort.getOrder(idOrder)).thenReturn(mockOrder);

        orderHandler.takeOrder(idOrder, idEmployee);

        verify(orderServicePort).saveOrder(mockOrder);
        assertEquals(State.EN_PREPARACION, mockOrder.getState());
    }

    @Test
    void testMarkAsReady() {
        Long orderId = 1L;
        Long clientId = 2L;
        int pin = 1234;

        OrderModel order = new OrderModel();
        order.setId(orderId);
        order.setIdClient(clientId);
        order.setState(State.PENDIENTE);

        UserDto client = new UserDto();
        client.setPhone("123456789");

        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(client, HttpStatus.OK);

        when(orderServicePort.getOrder(orderId)).thenReturn(order);
        when(userFeignClient.getUser(clientId)).thenReturn(responseEntity);

        orderHandler.markAsReady(orderId);

        verify(orderServicePort).saveOrder(order);
    }

    @Test
    void testDeliverValidPin() {
        Long orderId = 1L;
        int pin = 1234;

        OrderModel order = new OrderModel();
        order.setId(orderId);
        order.setPin(pin);
        order.setState(State.LISTO);

        when(orderServicePort.getOrder(orderId)).thenReturn(order);
        orderHandler.deliver(orderId, pin);

        verify(orderServicePort).saveOrder(order);
    }

    @Test
    void testDeliverInvalidPin() {
        Long orderId = 1L;
        int validPin = 1234;
        int invalidPin = 5678;

        OrderModel order = new OrderModel();
        order.setId(orderId);
        order.setPin(validPin);
        order.setState(State.LISTO);

        when(orderServicePort.getOrder(orderId)).thenReturn(order);

        assertThrows(InvalidPinException.class, () -> orderHandler.deliver(orderId, invalidPin));
    }

    @Test
    void testDeliverInvalidState() {
        Long orderId = 1L;
        int pin = 1234;

        OrderModel order = new OrderModel();
        order.setId(orderId);
        order.setPin(pin);
        order.setState(State.PENDIENTE);

        when(orderServicePort.getOrder(orderId)).thenReturn(order);

        assertThrows(InvalidStateException.class, () -> orderHandler.deliver(orderId, pin));
    }

    @Test
    void testCancelOrderPendingState() {
        Long clientId = 2L;

        OrderModel order = new OrderModel();
        order.setIdClient(clientId);
        order.setState(State.PENDIENTE);

        when(orderServicePort.getOrderByIdClient(clientId)).thenReturn(order);

        String result = orderHandler.cancelOrder(clientId);

        assertEquals("Orden Cancelada", result);
        assertEquals(State.CANCELADO, order.getState());
        verify(orderServicePort).updateOrder(order);
    }

    @Test
    void testCancelOrderAlreadyCancelled() {
        Long clientId = 2L;

        OrderModel order = new OrderModel();
        order.setIdClient(clientId);
        order.setState(State.CANCELADO);

        when(orderServicePort.getOrderByIdClient(clientId)).thenReturn(order);

        String result = orderHandler.cancelOrder(clientId);

        assertEquals("Su pedido ya ha sido cancelado", result);
        assertEquals(State.CANCELADO, order.getState());
        verify(orderServicePort, never()).updateOrder(order);
    }

    @Test
    void testCancelOrderPreparationInProgress() {
        Long clientId = 2L;

        OrderModel order = new OrderModel();
        order.setIdClient(clientId);
        order.setState(State.LISTO);

        when(orderServicePort.getOrderByIdClient(clientId)).thenReturn(order);

        String result = orderHandler.cancelOrder(clientId);

        assertEquals("Lo sentimos, tu pedido ya esta en preparacion y no puede cancelarse", result);
        assertEquals(State.LISTO, order.getState());
        verify(orderServicePort, never()).updateOrder(order);
    }
}