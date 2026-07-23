package com.disaster.alertservice.dto;

import com.disaster.alertservice.entity.AlertType;
import com.disaster.alertservice.entity.Severity;
import com.disaster.alertservice.entity.SourceService;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlertResponseDto {

    private Long alertId;

    private AlertType alertType;

    private SourceService sourceService;

    private Long sourceId;

    private Severity severity;

    private String message;

    private Boolean isAcknowledged;

    private LocalDateTime createdAt;
}
