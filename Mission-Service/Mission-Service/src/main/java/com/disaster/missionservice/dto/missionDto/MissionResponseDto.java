package com.disaster.missionservice.dto.missionDto;

import com.disaster.missionservice.entity.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MissionResponseDto {

    private Long missionId;

    private Long incidentId;

    private String title;

    private DisasterName disasterName;

    private String description;

    private String Location;

    private Priority priority;

    private MissionStatus missionStatus;

    private Boolean escalated;

    private LocalDateTime createdAt;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

}
