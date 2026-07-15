package com.disaster.incidentservice.controller;

import com.disaster.incidentservice.dto.IncidentRequestDto;
import com.disaster.incidentservice.dto.IncidentResponseDto;
import com.disaster.incidentservice.service.IncidentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incident")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @PostMapping
    public ResponseEntity<IncidentResponseDto> createIncident(@Valid @RequestBody IncidentRequestDto incidentRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(incidentService.createIncident(incidentRequestDto));
    }

    @GetMapping("/{incidentId}")
    public ResponseEntity<IncidentResponseDto> getIncident(@PathVariable Long incidentId) {

        return ResponseEntity.ok(incidentService.getIncidentById(incidentId));
    }

    @GetMapping
    public ResponseEntity<List<IncidentResponseDto>> getAllIncidents() {

        return ResponseEntity.ok(incidentService.getAllIncident());
    }

    @PutMapping("/{incidentId}")
    public ResponseEntity<IncidentResponseDto> updateIncident(@PathVariable Long incidentId, @RequestBody IncidentRequestDto incidentRequestDto) {

        return ResponseEntity.ok(incidentService.updateIncident(incidentId, incidentRequestDto));
    }

    @DeleteMapping("/{incidentId}")
    public ResponseEntity<String> deleteIncident(@PathVariable Long incidentId) {

        incidentService.deleteIncidentById(incidentId);
        return ResponseEntity.ok("Incident deleted successfully");
    }

}
