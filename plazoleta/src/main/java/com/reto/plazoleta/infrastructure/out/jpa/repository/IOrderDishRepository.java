package com.reto.plazoleta.infrastructure.out.jpa.repository;

import com.reto.plazoleta.infrastructure.out.jpa.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity, Long> {
    List<OrderDishEntity> findByOrderId(Long idOrder);
}
