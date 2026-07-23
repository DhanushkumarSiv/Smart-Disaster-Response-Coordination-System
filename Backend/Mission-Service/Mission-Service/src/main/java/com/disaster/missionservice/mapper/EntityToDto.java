package com.disaster.missionservice.mapper;
import com.disaster.missionservice.dto.missionDto.MissionResourceResponseDto;
import com.disaster.missionservice.dto.missionDto.MissionResponseDto;
import com.disaster.missionservice.dto.missionDto.MissionTeamsResponseDto;
import com.disaster.missionservice.entity.MissionResource;
import com.disaster.missionservice.entity.MissionTeams;
import com.disaster.missionservice.entity.Missions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityToDto {

    @Mapping(source = "mission.missionId", target = "missionId")
    MissionResourceResponseDto toMissionResourceResponseDto(MissionResource missionResource);

    @Mapping(source = "mission.missionId", target = "missionId")
    MissionTeamsResponseDto toMissionTeamsResponseDto(MissionTeams missionTeams);

    MissionResponseDto toMissionResponseDto(Missions mission);


}
