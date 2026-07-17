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

    @OneToMany(mappedBy = "missions", cascade = CascadeType.ALL)
    private List<MissionTeams> missionTeams;

    @OneToMany(mappedBy = "missions", cascade = CascadeType.ALL)
    private List<MissionResource> missionResources;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
