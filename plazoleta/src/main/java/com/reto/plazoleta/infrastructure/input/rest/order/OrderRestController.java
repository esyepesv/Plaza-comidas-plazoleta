package com.reto.plazoleta.infrastructure.input.rest.order;

import com.reto.plazoleta.application.auth.JwtService;
import com.reto.plazoleta.application.dto.request.order.OrderDto;
import com.reto.plazoleta.application.dto.response.DishResponse;
import com.reto.plazoleta.application.dto.response.RestaurantResponse;
import com.reto.plazoleta.application.handler.IDishHandler;
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
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class OrderRestController {

    private final IDishHandler dishHandler;
    private final JwtService jwtService;

    @Operation(summary = "Get all restaurant´s dishes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All dishes returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RestaurantResponse.class)))),
            //@ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/menu")
    public ResponseEntity<List<DishResponse>> getRestaurantDishes(
            @RequestParam Long idRestaurant,
            @RequestParam int nElements,
            @RequestParam String category
    ){
        return ResponseEntity.ok(dishHandler.getRestaurantDishes(idRestaurant, nElements, category));
    }

    @PostMapping("/realizarPedido")
    public ResponseEntity<Void> makeOrder(@RequestBody OrderDto order,
                                          @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        Long idClient = jwtService.extractId(token);
        order.setIdClient(idClient);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
