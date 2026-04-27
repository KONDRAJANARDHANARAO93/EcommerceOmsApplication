package com.ecommerce_oms.domain.repository;

import com.ecommerce_oms.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
