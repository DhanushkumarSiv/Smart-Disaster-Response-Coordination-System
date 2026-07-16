package com.disaster.resourceservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mapping")
public class Mapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mappingId;

    private Integer priorityScore;

    private Double defaultQuantityPerPerson;

    private Integer defaultDurationDays;

    private Boolean isMandatory;

    @ManyToOne
    @JoinColumn(name = "resourceId")
    private Resource resource;
}
