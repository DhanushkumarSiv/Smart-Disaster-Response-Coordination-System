package com.disaster.missionservice.service;

import com.disaster.missionservice.dto.missionDto.MissionResourceResponseDto;
import com.disaster.missionservice.dto.missionDto.MissionTeamsResponseDto;
import com.disaster.missionservice.entity.MissionResource;
import com.disaster.missionservice.entity.MissionTeams;
import com.disaster.missionservice.exception.MissionNotFoundException;
import com.disaster.missionservice.mapper.EntityToDto;
import com.disaster.missionservice.repository.MissionResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionResourceService {

    @Autowired
    private MissionResourceRepository missionResourceRepository;

    @Autowired
    private EntityToDto entityToDto;

    public MissionResourceResponseDto getMissionResourceById(Long missionId) {

        MissionResource resource = missionResourceRepository
                .findByMission_MissionId(missionId)
                .orElseThrow(() -> new MissionNotFoundException("Mission resource not found"));

        return entityToDto.toMissionResourceResponseDto(resource);

    }
}
