package com.ecommerce_oms.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "order")
    private List<OrderItem> OrderItems= new ArrayList<>();;
    @Column(nullable = false,length = 50)
    private String status;
    @Column(name="total_amount",precision = 12,scale = 2,nullable = false)
    private BigDecimal totalAmount;
    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;
    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return OrderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        OrderItems = orderItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

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
