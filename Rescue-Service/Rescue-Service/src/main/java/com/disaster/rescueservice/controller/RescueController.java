package com.disaster.rescueservice.controller;

import com.disaster.rescueservice.dto.RescueRequestDto;
import com.disaster.rescueservice.dto.RescueResponseDto;
import com.disaster.rescueservice.service.RescueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rescueTeam")
public class RescueController {

    @Autowired
    private RescueService rescueService;

    @PostMapping
    public ResponseEntity<RescueResponseDto> createRescue(@Valid @RequestBody RescueRequestDto rescueRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(rescueService.createRescueTeam(rescueRequestDto));
    }

    @GetMapping("/{rescueId}")
    public ResponseEntity<RescueResponseDto> getRescueTeamById(@PathVariable Long rescueId) {

        return ResponseEntity.ok(rescueService.getRescueTeamById(rescueId));
    }

    @GetMapping
    public ResponseEntity<List<RescueResponseDto>> getAllRescueTeams() {

        return ResponseEntity.ok(rescueService.getAllRescueTeams());
    }

    @PutMapping("/{rescueId}")
    public ResponseEntity<RescueResponseDto> updateRescueTeam(@PathVariable Long rescueId, @RequestBody RescueRequestDto rescueRequestDto) {

        return ResponseEntity.ok(rescueService.updateRescueTeam(rescueId, rescueRequestDto));
    }

    @DeleteMapping("/{rescueId}")
    public ResponseEntity<String> deleteRescue(@PathVariable Long rescueId) {

        rescueService.deleteRescueTeamById(rescueId);
        return ResponseEntity.ok("Team deleted successfully");
    }
}
