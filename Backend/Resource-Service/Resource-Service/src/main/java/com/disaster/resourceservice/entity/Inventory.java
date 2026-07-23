package com.disaster.resourceservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private String location;

    private Double totalQuantity;

    private Double reservedQuantity;

    private Double availableQuantity;

    private Double reorderLevel;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "resourceId")
    private Resource resource;
}
