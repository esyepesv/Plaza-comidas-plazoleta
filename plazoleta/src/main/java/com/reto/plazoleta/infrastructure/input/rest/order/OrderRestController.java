package com.reto.plazoleta.infrastructure.input.rest.order;

import com.reto.plazoleta.application.dto.request.order.OrderDto;
import com.reto.plazoleta.application.dto.response.DishResponse;
import com.reto.plazoleta.application.dto.response.order.OrderResponse;
import com.reto.plazoleta.application.handler.IDishHandler;
import com.reto.plazoleta.application.handler.IOrderHandler;
import com.reto.plazoleta.domain.model.State;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final IDishHandler dishHandler;
    private final IOrderHandler orderHandler;

    @Operation(summary = "Get all restaurant´s dishes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All dishes returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DishResponse.class)))),
    })
    @GetMapping("/menu")
    public ResponseEntity<List<DishResponse>> getRestaurantDishes(
            @RequestParam Long idRestaurant,
            @RequestParam int nElements,
            @RequestParam String category
    ){
        return ResponseEntity.ok(dishHandler.getRestaurantDishes(idRestaurant, nElements, category));
    }

    @Operation(summary = "Make a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created", content = @Content),
    })
    @PostMapping("/make-order")
    public ResponseEntity<Void> makeOrder(@RequestBody OrderDto order) {
        orderHandler.saveOrder(order);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all restaurant´s orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All orders returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderResponse.class)))),
    })
    @GetMapping("/get-restaurants-orders")
    public ResponseEntity<List<OrderResponse>> getRestaurantsOrders(
            @RequestParam Long idRestaurant,
            @RequestParam int nElements,
            @RequestParam State state
    ) {
        return ResponseEntity.ok(orderHandler.getRestaurantOrders(idRestaurant, nElements, state));
    }

    @Operation(summary = "Take order and change state")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order taken", content = @Content),
    })
    @PutMapping("/take-order")
    public ResponseEntity<Void> takeOrder(@RequestParam Long idOrder, @RequestParam Long idEmployee) {
        orderHandler.takeOrder(idOrder, idEmployee);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Mark order as ready and send notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order set as ready", content = @Content),
    })
    @PutMapping("/mark-as-ready")
    public ResponseEntity<Void> markAsReady(@RequestParam Long idOrder){

        orderHandler.markAsReady(idOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Mark order as delivered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order set as delivered", content = @Content),
    })
    @PutMapping("/deliver")
    public ResponseEntity<Void> deliver(@RequestParam Long idOrder, @RequestParam int pin){

        orderHandler.deliver(idOrder, pin);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Cancel order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order canceled", content = @Content),
    })
    @PutMapping("/cancel")
    public ResponseEntity<String> cancerOrder(@RequestParam Long idClient) {

       String response = orderHandler.cancelOrder(idClient);

        return ResponseEntity.ok(response);
    }


}
