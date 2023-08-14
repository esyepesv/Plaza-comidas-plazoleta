package com.reto.plazoleta.domain.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantModel {
    private Long id;
    private String name;
    private String direction;
    private Long idOwner;
    private String phone;
    private String urlLogo;
    private String nit;
}
