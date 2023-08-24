package com.reto.plazoleta.infrastructure.out.jpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String direction;

    private Long idOwner;

    private String phone;

    private String urlLogo;

    private String nit;
}
