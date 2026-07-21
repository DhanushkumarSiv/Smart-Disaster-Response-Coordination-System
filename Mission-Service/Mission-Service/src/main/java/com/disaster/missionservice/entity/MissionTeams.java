package com.disaster.missionservice.entity;

import com.disaster.missionservice.dto.rescueTeamDto.RescueTeamStatus;
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

    private String missionTeamName;

    private LocalDateTime assignedAt;

    private LocalDateTime releasedAt;

    @OneToOne
    @JoinColumn(name = "missionId")
    private Missions mission;
}
