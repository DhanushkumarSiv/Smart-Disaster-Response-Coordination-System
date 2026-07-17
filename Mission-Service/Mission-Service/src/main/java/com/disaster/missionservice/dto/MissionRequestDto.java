package com.disaster.missionservice.dto;

import com.disaster.missionservice.entity.MissionStatus;
import com.disaster.missionservice.entity.Priority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MissionRequestDto {

    private Long incidentId;

    private String title;

    private String description;

    private String Location;

    private Priority priority;

    private MissionStatus missionStatus;

    private Boolean escalated;

    private LocalDateTime createdAt;
}
