package com.reto.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishModel {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String urlImage;
    private String category;
    private boolean active = true;
    private Long idRestaurant;
}
