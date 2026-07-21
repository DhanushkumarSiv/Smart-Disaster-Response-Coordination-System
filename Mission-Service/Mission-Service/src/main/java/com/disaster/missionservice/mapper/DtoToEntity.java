package com.disaster.missionservice.mapper;

import com.disaster.missionservice.dto.missionDto.MissionRequestDto;
import com.disaster.missionservice.dto.missionDto.MissionResourceRequestDto;
import com.disaster.missionservice.dto.missionDto.MissionTeamRequestDto;
import com.disaster.missionservice.entity.MissionResource;
import com.disaster.missionservice.entity.MissionTeams;
import com.disaster.missionservice.entity.Missions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoToEntity {

    Missions toMissions(MissionRequestDto missionRequestDto);

    MissionTeams toMissionTeams(MissionTeamRequestDto missionTeamRequestDto);

    MissionResource toMissionResource(MissionResourceRequestDto missionResourceRequestDto);
}
