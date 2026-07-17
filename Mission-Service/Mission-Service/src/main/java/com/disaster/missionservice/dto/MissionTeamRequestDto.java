package com.disaster.missionservice.dto;

import com.disaster.missionservice.entity.TeamStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MissionTeamRequestDto {

    private Long rescueTeamId;

    private LocalDateTime assignedAt;

    private TeamStatus teamStatus;

    private LocalDateTime releasedAt;

    private Long missionId;
}
