package com.disaster.missionservice.dto.missionDto;

import com.disaster.missionservice.dto.rescueTeamDto.RescueTeamStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MissionTeamsResponseDto {

    private Long rescueTeamId;

    private LocalDateTime assignedAt;

    private String missionTeamName;

    private LocalDateTime releasedAt;

    private Long missionId;
}
