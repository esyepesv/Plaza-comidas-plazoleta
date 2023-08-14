package com.reto.plazoleta.infrastructure.input.rest.dish;

import com.reto.plazoleta.application.auth.JwtService;
import com.reto.plazoleta.application.dto.request.dish.DishRequestDto;
import com.reto.plazoleta.application.dto.request.dish.DishUpdateRequestDto;
import com.reto.plazoleta.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platos")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;
    private final JwtService jwtService;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
            //@ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PostMapping("/crearPlato")
    public ResponseEntity<Void> saveDish(
            @Validated @RequestBody DishRequestDto dishRequestDto,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.substring("Bearer ".length());
        Long idOwner = jwtService.extractId(token);
        dishHandler.saveDish(dishRequestDto, idOwner);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Update dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "dish updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "dish not found", content = @Content)
    })
    @PutMapping("/modificarPlato")
    public ResponseEntity<Void> updateDish(
            @RequestBody DishUpdateRequestDto dishUpdateRequestDto,
            @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        Long idOwner = jwtService.extractId(token);
        dishHandler.updateDish(dishUpdateRequestDto, idOwner);
        return ResponseEntity.ok().build();
    }
}
