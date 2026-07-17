package com.disaster.missionservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "mission_teams")
public class MissionTeams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionTeamId;

    private Long rescueTeamId;

    private LocalDateTime assignedAt;

    @Enumerated(EnumType.STRING)
    private TeamStatus teamStatus;

    private LocalDateTime releasedAt;

    @ManyToOne
    @JoinColumn(name = "missionId")
    private Missions mission;
}
