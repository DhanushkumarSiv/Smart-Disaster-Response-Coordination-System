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

    private Double quantity;

    private ResourceStatus resourceStatus;

    @OneToOne
    @JoinColumn(name = "missionId")
    private Missions mission;
}
