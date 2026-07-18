package com.disaster.missionservice.mapper;

import com.disaster.missionservice.dto.MissionRequestDto;
import com.disaster.missionservice.dto.MissionTeamRequestDto;
import com.disaster.missionservice.entity.MissionTeams;
import com.disaster.missionservice.entity.Missions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoToEntity {

    Missions toMissions(MissionRequestDto missionRequestDto);

    MissionTeams toMissionTeams(MissionTeamRequestDto missionTeamRequestDto);
}
