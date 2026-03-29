package com.ecommerce_oms.domain.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name="inventory")
public class Inventory {
    @Id
    @Column(name="product_id",nullable = false)
   private Long productId;

    @OneToOne
    @MapsId
    @JoinColumn(name="product_id")
    private Product product;
    @Column(name="available_quantity",nullable = false)
   private  int availableQuantity;
    @Column(name="reserved_quantity",nullable =false)
    private int reservedQuantity =0;
    @Column(nullable = false)
    @Version
    private int version;
    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;
    @PreUpdate
    @PrePersist
    protected void onSave(){
        this.updatedAt=LocalDateTime.now();

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
