package com.disaster.missionservice.service;

import com.disaster.missionservice.dto.missionDto.MissionTeamsResponseDto;
import com.disaster.missionservice.entity.MissionTeams;
import com.disaster.missionservice.entity.Missions;
import com.disaster.missionservice.exception.MissionNotFoundException;
import com.disaster.missionservice.mapper.EntityToDto;
import com.disaster.missionservice.repository.MissionRepository;
import com.disaster.missionservice.repository.MissionTeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionTeamService {


    @Autowired
    private MissionTeamsRepository missionTeamRepository;

    @Autowired
    private EntityToDto entityToDto;

    public MissionTeamsResponseDto getMissionTeamById(Long missionId) {

        MissionTeams team = missionTeamRepository
                .findByMission_MissionId(missionId)
                .orElseThrow(() -> new MissionNotFoundException("Mission team not found"));

        return entityToDto.toMissionTeamsResponseDto(team);

    }
}
