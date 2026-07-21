package com.disaster.incidentservice.dto;

import com.disaster.incidentservice.entity.Disaster;
import com.disaster.incidentservice.entity.Severity;
import com.disaster.incidentservice.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidentResponseDto {

    private Long incidentId;

    private String reporterName;

    private String reporterMobileNumber;

    private Disaster disasterName;

    private String description;

    private String location;

    private Severity severity;

    private Status status;

    private LocalDateTime createdAt;
}
