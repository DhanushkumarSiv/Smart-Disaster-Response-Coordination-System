package com.disaster.missionservice.dto.resourceDto;

import lombok.Data;

import java.util.List;

@Data
public class ResourceDto {

    private Long resourceId;

    private String resourceCode;

    private String name;

    private String description;

    private Category category;

    private String unitOfMeasure;

    private Boolean isActive;
}
