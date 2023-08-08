package com.reto.plazoleta.application.dto.request;

import com.reto.plazoleta.application.validation.NotNumericOnly;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class RestaurantRequestDto {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @NotNumericOnly
    private String name;

    @NotBlank(message = "La dirección es obligatoria")
    private String direction;

    @NotNull(message = "El id del propietario es obligatorio")
    private Long idOwner;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\+?[0-9]{1,13}", message = "El teléfono debe ser numérico y tener un máximo de 13 caracteres, puede incluir +")
    private String phone;

    @NotBlank(message = "La URL del logo es obligatoria")
    private String urlLogo;

    @NotBlank(message = "El NIT es obligatorio")
    @Pattern(regexp = "[0-9]+", message = "El NIT debe ser numérico")
    private String nit;
}

