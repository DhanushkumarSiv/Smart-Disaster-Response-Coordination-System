package com.disaster.missionservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "missions")
public class Missions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    private Long incidentId;

    private String title;

    private DisasterName disasterName;

    private String description;

    private String Location;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private MissionStatus missionStatus;

    private Boolean escalated;

    private LocalDateTime createdAt;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    @OneToOne(mappedBy = "mission", cascade = CascadeType.ALL)
    private MissionTeams missionTeams;

    @OneToOne(mappedBy = "mission", cascade = CascadeType.ALL)
    private MissionResource missionResources;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
