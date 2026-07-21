package com.disaster.resourceservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    private String resourceCode;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String unitOfMeasure;

    private Boolean isActive;

    @OneToOne(mappedBy = "resource", cascade = CascadeType.ALL)
    private Inventory inventories;

}
