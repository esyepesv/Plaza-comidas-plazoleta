package com.reto.plazoleta.application.handler;

import com.reto.plazoleta.application.dto.UserDto;
import com.reto.plazoleta.application.dto.request.SMSSendRequest;
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
import com.reto.plazoleta.infrastructure.exception.NoDataFoundException;
import com.reto.plazoleta.infrastructure.out.feign.MessageFeignClient;
import com.reto.plazoleta.infrastructure.out.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler{

    private final IOrderServicePort orderServicePort;
    private final IOrderDishServicePort orderDishServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;
    private final IDishServicePort dishServicePort;
    private final MessageFeignClient messageFeignClient;
    private final UserFeignClient userFeignClient;

    @Override
    public void saveOrder(OrderDto orderDto) {
        OrderModel order = orderRequestMapper.toOrder(orderDto);
        List<OrderDishModel> orderDishes= orderRequestMapper.toOrderDishList(orderDto.getOrderDishes());

        order.setDate(new Date());
        order.setState(State.PENDIENTE);

        order = orderServicePort.saveOrder(order);
        for(OrderDishModel orderDish: orderDishes) {
            orderDish.setOrderId(order.getId());
            DishModel dish = dishServicePort.getDish(orderDish.getIdDish());
            orderDish.setName(dish.getName());
        }
        orderDishServicePort.saveOrderDish(orderDishes);
    }

    @Override
    public List<OrderResponse> getRestaurantOrders(Long idRestaurant, int nElements, State state) {
        List<OrderModel> orders = orderServicePort.getRestaurantOrders(idRestaurant);

        List<OrderResponse> response = orderResponseMapper.toOrderResponse(orders);

        List<OrderResponse> filteredResponse = new ArrayList<>();
        int count = 0;

        for (OrderResponse order : response) {
            if (order.getState() == state && count < nElements) {
                List<OrderDishModel> orderDishes = orderDishServicePort.getOrderDishes(order.getId());
                order.setOrderDishes(orderResponseMapper.toOrderDishResponse(orderDishes));
                filteredResponse.add(order);
                count++;
            }
        }


        return filteredResponse;
    }

    @Override
    public void takeOrder(Long idOrder, Long idEmployee) {
        OrderModel order = orderServicePort.getOrder(idOrder);
        order.setIdChef(idEmployee);
        order.setState(State.EN_PREPARACION);
        orderServicePort.saveOrder(order);
    }

    @Override
    public void markAsReady(Long idOrder) {
        OrderModel order = orderServicePort.getOrder(idOrder);
        int pin = new Random().nextInt(10000);
        order.setPin(pin);
        order.setState(State.LISTO);
        orderServicePort.saveOrder(order);
        ResponseEntity<UserDto> response = userFeignClient.getUser(order.getIdClient());
        if(response.getStatusCode().is2xxSuccessful()){
            UserDto client = response.getBody();
            messageFeignClient.process(new SMSSendRequest(client.getPhone(), "Pin: " +pin));
        }
        else throw new NoDataFoundException();
    }

    @Override
    public void deliver(Long idOrder, int pin) {
        OrderModel order = orderServicePort.getOrder(idOrder);

        if(pin == order.getPin()){
            if(order.getState().equals(State.LISTO)){
                order.setState(State.ENTREGADO);
                orderServicePort.saveOrder(order);
            }
            else throw new InvalidStateException();
        }
        else throw new InvalidPinException();
    }

    @Override
    public String cancelOrder(Long idClient) {
        OrderModel order = orderServicePort.getOrderByIdClient(idClient);
        if (order.getState().equals(State.PENDIENTE)){
            order.setState(State.CANCELADO);
            orderServicePort.updateOrder(order);
            return "Orden Cancelada";
        } else if (order.getState().equals(State.CANCELADO)) {
            return "Su pedido ya ha sido cancelado";
        }
        return "Lo sentimos, tu pedido ya esta en preparacion y no puede cancelarse";
    }
}
