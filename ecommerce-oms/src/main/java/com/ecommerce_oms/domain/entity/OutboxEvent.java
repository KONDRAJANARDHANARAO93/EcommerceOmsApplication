package com.ecommerce_oms.domain.entity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.time.LocalDateTime;

@Entity
@Table(name="outbox_events")
public class OutboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="aggregate_type",nullable = false)
    private String aggregateType;
    @Column(name="aggregate_id",nullable = false)
    private Long aggregateId;
    @Column(name="event_type",nullable = false)
    private String eventType;

   @Column(nullable = false, columnDefinition = "TEXT")
    private String payload;

    @Column(nullable = false)
    private String status;
    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @PrePersist

    protected void onSave(){
        this.createdAt=LocalDateTime.now();
    }

}
