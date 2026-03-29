package com.ecommerce_oms.domain.entity;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name= "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal price;
    @Column(nullable = false)
   private boolean active=true;
    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;



    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }



}

