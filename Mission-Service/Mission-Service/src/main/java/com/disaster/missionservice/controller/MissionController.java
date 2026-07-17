package com.disaster.missionservice.controller;

import com.disaster.missionservice.dto.MissionRequestDto;
import com.disaster.missionservice.dto.MissionResponseDto;
import com.disaster.missionservice.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mission")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @PostMapping
    public MissionResponseDto createMission() {


    }

}
