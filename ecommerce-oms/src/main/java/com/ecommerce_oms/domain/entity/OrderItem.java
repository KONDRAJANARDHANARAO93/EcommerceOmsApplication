package com.ecommerce_oms.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name="order_items")


public class OrderItem {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;
    @Column(name="price_at_order_time",nullable = false,precision = 10,scale = 2)
    private BigDecimal priceAtOrderTime;

    @ManyToOne(optional = false)
    @JoinColumn(name="order_id",nullable = false)
    private Order order;
    @ManyToOne(optional = false)
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

}
