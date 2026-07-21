package com.disaster.missionservice.dto.missionDto;

import com.disaster.missionservice.entity.MissionStatus;
import lombok.Data;

@Data
public class UpdateMissionDto {

    private Long missionId;

    private MissionStatus missionStatus;


}
