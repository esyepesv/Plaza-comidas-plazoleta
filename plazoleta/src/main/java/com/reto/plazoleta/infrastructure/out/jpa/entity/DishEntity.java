package com.reto.plazoleta.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Dishes")
@Getter
@Setter
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    private String description;
    private String urlImage;
    private String category;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "id_restaurant")
    private Long idRestaurant;

}
