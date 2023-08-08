package com.reto.plazoleta.infrastructure.input.rest.dish;

import com.reto.plazoleta.application.dto.request.DishRequestDto;
import com.reto.plazoleta.application.dto.request.RestaurantRequestDto;
import com.reto.plazoleta.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platos")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
            //@ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PostMapping("/crearPlato")
    public ResponseEntity<Void> saveDish(@Validated @RequestBody DishRequestDto dishRequestDto) {
        dishHandler.saveDish(dishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
