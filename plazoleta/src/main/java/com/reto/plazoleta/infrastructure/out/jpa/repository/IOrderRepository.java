package com.reto.plazoleta.infrastructure.out.jpa.repository;

import com.reto.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByIdRestaurant(Long idRestaurant);
}
