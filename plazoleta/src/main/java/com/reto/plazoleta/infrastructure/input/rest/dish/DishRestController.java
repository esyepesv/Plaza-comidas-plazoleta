package com.reto.plazoleta.infrastructure.input.rest.dish;

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
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    int tokenStart = 7;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
    })
    @PostMapping("/create-dish")
    public ResponseEntity<Void> saveDish(
            @Validated @RequestBody DishRequestDto dishRequestDto
    ) {
        dishHandler.saveDish(dishRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "dish updated", content = @Content),
    })
    @PutMapping("/update-dish")
    public ResponseEntity<Void> updateDish( @RequestBody DishUpdateRequestDto dishUpdateRequestDto) {
        dishHandler.updateDish(dishUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Activate/deactivate dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "dish updated", content = @Content),
    })
    @PutMapping("/enable-dish")
    public ResponseEntity<Void> enableDish(
            @RequestParam Long id,
            @RequestParam boolean isActive) {
        dishHandler.enableDish(id, isActive);
        return ResponseEntity.ok().build();
    }
}
