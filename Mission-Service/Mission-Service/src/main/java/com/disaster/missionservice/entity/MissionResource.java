package com.disaster.missionservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mission_resource")
public class MissionResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionResourceId;

    private Long resourceId;

    private Double quantityAllocated;

    private Double quantityConsumed;

    private Double quantityReturned;

    private ResourceStatus missionStatus;

    @ManyToOne
    @JoinColumn(name = "missionId")
    private Missions mission;
}
