package com.disaster.missionservice.controller;

import com.disaster.missionservice.dto.missionDto.MissionTeamsResponseDto;
import com.disaster.missionservice.service.GetMissionService;
import com.disaster.missionservice.service.MissionTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mission/teams")
public class MissionTeamController {

    @Autowired
    private MissionTeamService missionTeamService;

    @GetMapping("/{missionId}")
    public MissionTeamsResponseDto getMissionTeamById(@PathVariable Long missionId) {

        return missionTeamService.getMissionTeamById(missionId);
    }

}
