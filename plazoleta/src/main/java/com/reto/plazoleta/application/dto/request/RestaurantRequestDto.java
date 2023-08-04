package com.reto.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequestDto {
    private Long id;
    private String name;
    private String direction;
    private Long idPropietario;
    private String phone;
    private String urlLogo;
    private String nit;
}
