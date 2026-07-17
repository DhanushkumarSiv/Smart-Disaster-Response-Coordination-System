package com.disaster.alertservice.dto;

import com.disaster.alertservice.entity.AlertType;
import com.disaster.alertservice.entity.Severity;
import com.disaster.alertservice.entity.SourceService;
import lombok.Data;


@Data
public class AlertRequestDto {

    private AlertType alertType;

    private SourceService sourceService;

    private Long sourceId;

    private Severity severity;

    private String message;

    private Boolean isAcknowledged;

}
