package com.disaster.missionservice.dto;

import com.disaster.missionservice.entity.ResourceStatus;
import lombok.Data;

@Data
public class MissionResourceRequestDto {

    private Long resourceId;

    private Double quantityAllocated;

    private Double quantityConsumed;

    private Double quantityReturned;

    private ResourceStatus missionStatus;
}
