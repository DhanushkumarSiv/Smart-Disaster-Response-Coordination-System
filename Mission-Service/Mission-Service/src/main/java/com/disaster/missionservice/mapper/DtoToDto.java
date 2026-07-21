package com.disaster.missionservice.mapper;

import com.disaster.missionservice.dto.missionDto.MissionTeamRequestDto;
import com.disaster.missionservice.dto.missionDto.MissionTeamsResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoToDto {

    MissionTeamsResponseDto toMissionTeamsResponseDto(MissionTeamRequestDto missionTeamRequestDto);
}
