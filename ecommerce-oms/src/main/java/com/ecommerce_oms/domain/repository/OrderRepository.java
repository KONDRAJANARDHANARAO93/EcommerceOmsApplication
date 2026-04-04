package com.ecommerce_oms.domain.repository;

import com.ecommerce_oms.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
