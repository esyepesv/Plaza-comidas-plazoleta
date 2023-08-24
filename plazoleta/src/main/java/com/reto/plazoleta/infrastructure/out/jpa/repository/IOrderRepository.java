package com.reto.plazoleta.infrastructure.out.jpa.repository;

import com.reto.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByIdRestaurant(Long idRestaurant);

    Optional<OrderEntity> findByIdClient(Long idClient);
}
