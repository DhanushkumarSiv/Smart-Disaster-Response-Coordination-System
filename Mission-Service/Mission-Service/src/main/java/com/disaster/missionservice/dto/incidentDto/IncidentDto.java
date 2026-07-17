package com.disaster.missionservice.dto.incidentDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidentDto {

    private Long incidentId;

    private String reporterName;

    private String reporterMobileNumber;

    private IncidentDisaster disasterName;

    private String description;

    private String location;

    private IncidentSeverity severity;

    private IncidentStatus status;

    private LocalDateTime createdAt;
}
