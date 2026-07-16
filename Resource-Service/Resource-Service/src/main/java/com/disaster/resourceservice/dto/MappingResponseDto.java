package com.disaster.resourceservice.dto;

import lombok.Data;

@Data
public class MappingResponseDto {

    private Long mappingId;

    private Integer priorityScore;

    private Double defaultQuantityPerPerson;

    private Integer defaultDurationDays;

    private Boolean isMandatory;

}
