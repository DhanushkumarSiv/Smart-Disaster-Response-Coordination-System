package com.disaster.resourceservice.dto;

import lombok.Data;

@Data
public class MappingRequestDto {

    private Integer priorityScore;

    private Double defaultQuantityPerPerson;

    private Integer defaultDurationDays;

    private Boolean isMandatory;

    private Long resourceId;
}
