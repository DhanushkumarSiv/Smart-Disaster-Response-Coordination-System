package com.disaster.missionservice.dto.missionDto;

import com.disaster.missionservice.entity.DisasterName;
import com.disaster.missionservice.entity.MissionStatus;
import com.disaster.missionservice.entity.Priority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MissionCreateResponseDto {

    private Long missionId;

    private Long incidentId;

    private String title;

    private DisasterName disasterName;

    private String description;

    private String Location;

    private Priority priority;

    private MissionStatus missionStatus;

    private LocalDateTime createdAt;

}
