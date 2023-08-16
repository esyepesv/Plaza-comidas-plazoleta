package com.reto.plazoleta.application.dto.response;

import lombok.Data;

@Data
public class DishResponse {
    private String name;
    private int price;
    private String description;
    private String urlImage;
    private String category;
}
