package com.disaster.missionservice.service;

import com.disaster.missionservice.dto.missionDto.MissionResponseDto;
import com.disaster.missionservice.entity.Missions;
import com.disaster.missionservice.exception.MissionNotFoundException;
import com.disaster.missionservice.mapper.EntityToDto;
import com.disaster.missionservice.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private EntityToDto entityToDto;

    public List<MissionResponseDto> getMissions() {

        List<Missions> missions = missionRepository.findAll();
        return missions.stream()
                .map(mission -> entityToDto.toMissionResponseDto(mission))
                .toList();
    }

    public MissionResponseDto getMissionById(Long missionId) {

        Missions mission = missionRepository.findById(missionId).
                orElseThrow(() -> new MissionNotFoundException("Mission Not Found"));
        return entityToDto.toMissionResponseDto(mission);
    }
}
