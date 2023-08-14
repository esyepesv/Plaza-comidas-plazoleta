package com.reto.plazoleta.domain.model;

import lombok.*;

@Data
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
