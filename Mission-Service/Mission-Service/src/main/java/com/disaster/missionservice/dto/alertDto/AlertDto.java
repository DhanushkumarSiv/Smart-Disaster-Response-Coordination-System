package com.disaster.missionservice.dto.alertDto;

import lombok.Data;

@Data
public class AlertDto {

    private String alertType;

    private String sourceService;

    private Long sourceId;

    private String severity;

    private String message;

    private Boolean isAcknowledged;
}
