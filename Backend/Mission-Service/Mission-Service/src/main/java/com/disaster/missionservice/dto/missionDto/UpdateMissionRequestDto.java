package com.disaster.missionservice.dto.missionDto;

import com.disaster.missionservice.entity.MissionStatus;
import lombok.Data;

@Data
public class UpdateMissionRequestDto {

    private MissionStatus missionStatus;
}
