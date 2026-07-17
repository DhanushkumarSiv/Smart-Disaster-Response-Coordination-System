package com.disaster.missionservice.dto.rescueTeamDto;

import lombok.Data;

@Data
public class RescueTeamDto {

    private Long teamId;

    private String teamName;

    private String location;

    private RescueTeamStatus status;

    private TeamDepartment department;
}
