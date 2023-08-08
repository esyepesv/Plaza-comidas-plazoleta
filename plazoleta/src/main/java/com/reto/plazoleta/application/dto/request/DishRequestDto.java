package com.reto.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DishRequestDto {
    private String name;
    private int price;
    private String description;
    private String urlImage;
    private String category;
    private boolean active;
    private Long idRestaurant;
}
