package com.ecommerce_oms.domain.entity;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @ManyToOne(optional = false)
   @JoinColumn(name = "order_id",nullable = false)
    private Order order;
   @Column(name="payment_reference",nullable = false,unique = true)
   private String paymentReference;
   @Column(nullable = false,precision = 12,scale = 2)
   private BigDecimal amount;
   @Column(nullable = false )
   private String status;
   @Column(name="idempotency_key",nullable = false,unique = true)
   private String idempotencyKey;
   @Column(name="created_at",nullable = false,updatable = false)
   private LocalDateTime createdAt;

    @PreUpdate
    @PrePersist
    protected void onSave(){
       this.createdAt=LocalDateTime.now();

   }

}
