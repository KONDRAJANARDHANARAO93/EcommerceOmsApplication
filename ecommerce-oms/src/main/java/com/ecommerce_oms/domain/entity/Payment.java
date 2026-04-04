package com.ecommerce_oms.domain.entity;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="payments")
public class Payment
{
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
