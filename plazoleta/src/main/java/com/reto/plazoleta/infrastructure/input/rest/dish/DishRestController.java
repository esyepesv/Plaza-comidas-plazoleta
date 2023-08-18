package com.reto.plazoleta.infrastructure.input.rest.dish;

import com.reto.plazoleta.infrastructure.configuration.security.JwtService;
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
    private final JwtService jwtService;
    int tokenStart = 7;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
    })
    @PostMapping("/create-dish")
    public ResponseEntity<Void> saveDish(
            @Validated @RequestBody DishRequestDto dishRequestDto,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.substring(tokenStart);
        Long idOwner = jwtService.extractId(token);
        dishHandler.saveDish(dishRequestDto, idOwner);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "dish updated", content = @Content),
    })
    @PutMapping("/update-dish")
    public ResponseEntity<Void> updateDish(
            @RequestBody DishUpdateRequestDto dishUpdateRequestDto,
            @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(tokenStart);
        Long idOwner = jwtService.extractId(token);
        dishHandler.updateDish(dishUpdateRequestDto, idOwner);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Activate/deactivate dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "dish updated", content = @Content),
    })
    @PutMapping("/enable-dish")
    public ResponseEntity<Void> enableDish(
            @RequestParam Long id,
            @RequestParam boolean isActive,
            @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(tokenStart);
        Long idOwner = jwtService.extractId(token);
        dishHandler.enableDish(id, isActive, idOwner);
        return ResponseEntity.ok().build();
    }





}
