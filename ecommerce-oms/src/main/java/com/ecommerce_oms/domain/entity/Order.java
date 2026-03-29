package com.ecommerce_oms.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="order_number",nullable = false,unique = true,length = 100)
    private String orderNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;
    @Column(nullable = false,length = 50)
    private String status;
    @Column(name="total_amount",precision = 12,scale = 2,nullable = false)
    private BigDecimal totalAmount;
    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;
    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @PrePersist

    protected  void onSave()
    {
        this.createdAt=LocalDateTime.now();
        this.updatedAt=createdAt;

    }
    @PreUpdate
    protected void onUpadte(){
        this.updatedAt=LocalDateTime.now();

    }

}
