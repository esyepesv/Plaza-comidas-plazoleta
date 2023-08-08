package com.reto.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class DishRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotNull
    @Min(value = 0)
    private int price;
    @NotBlank(message = "La descripción es obligatoria")
    private String description;
    @NotBlank(message = "La imagen es obligatoria")
    private String urlImage;
    @NotBlank(message = "La categoria es obligatoria")
    private String category;
}
