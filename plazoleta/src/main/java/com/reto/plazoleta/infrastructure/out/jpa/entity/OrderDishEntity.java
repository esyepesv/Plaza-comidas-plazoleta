package com.reto.plazoleta.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "orderDishes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idDish;
    private String name;
    private Integer number;

    @Column(name = "order_id")
    private Long orderId;

}
