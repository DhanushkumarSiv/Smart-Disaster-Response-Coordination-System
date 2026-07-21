package com.disaster.missionservice.controller;

import com.disaster.missionservice.dto.missionDto.MissionCreateResponseDto;
import com.disaster.missionservice.dto.missionDto.MissionRequestDto;
import com.disaster.missionservice.dto.missionDto.MissionResponseDto;
import com.disaster.missionservice.dto.missionDto.UpdateMissionRequestDto;
import com.disaster.missionservice.service.GetMissionService;
import com.disaster.missionservice.service.InitializeMissionService;
import com.disaster.missionservice.service.UpdateMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mission")
public class MissionController {

    @Autowired
    private InitializeMissionService initializeMissionService;

    @Autowired
    private UpdateMissionService updateMissionService;

    @Autowired
    private GetMissionService getMissionService;

    @PostMapping("/create")
    public Mono<MissionCreateResponseDto> createMission() {

        return initializeMissionService.initializeMission();
    }

    @PutMapping("update/{missionId}")
    public MissionResponseDto updateMission(@PathVariable Long missionId, @RequestBody UpdateMissionRequestDto updateMissionRequestDto) {

        return updateMissionService.startMission(missionId, updateMissionRequestDto);
    }

    @GetMapping
    public List<MissionResponseDto> getMission() {

        return getMissionService.getMissions();
    }

    @GetMapping("/{missionId}")
    public MissionResponseDto getMission(@PathVariable Long missionId) {

        return  getMissionService.getMissionById(missionId);
    }

}
