package com.disaster.missionservice.dto.missionDto;

import com.disaster.missionservice.entity.ResourceStatus;
import lombok.Data;

@Data
public class MissionResourceResponseDto {

    private Long missionId;

    private Long resourceId;

    private Double quantity;

    private ResourceStatus resourceStatus;
}
