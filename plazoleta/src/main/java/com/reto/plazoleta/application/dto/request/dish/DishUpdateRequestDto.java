package com.reto.plazoleta.application.dto.request.dish;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Setter
@Getter
public class DishUpdateRequestDto {
    @NotNull
    private Long id;
    private int price;
    private String description;
}
