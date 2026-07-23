package com.disaster.missionservice.controller;

import com.disaster.missionservice.dto.missionDto.MissionResourceResponseDto;
import com.disaster.missionservice.service.MissionResourceService;
import com.disaster.missionservice.service.MissionTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mission/resources")
public class MissionResourceController {

    @Autowired
    private MissionResourceService missionResourceService;

    @GetMapping("/{missionId}")
    public MissionResourceResponseDto getMissionResource(@PathVariable Long missionId) {

        return missionResourceService.getMissionResourceById(missionId);
    }
}
