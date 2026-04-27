package com.ecommerce_oms.domain.repository;

import com.ecommerce_oms.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
