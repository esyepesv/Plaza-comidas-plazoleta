package com.reto.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private String document;
    private String phone;
    private String birthDate;
    private String email;
    private String password;
    private Long idRol;
}
