package com.disaster.alertservice.controller;

import com.disaster.alertservice.dto.AlertRequestDto;
import com.disaster.alertservice.dto.AlertResponseDto;
import com.disaster.alertservice.service.AlertService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<AlertResponseDto> createAlert(@Valid @RequestBody AlertRequestDto alertRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(alertService.createAlert(alertRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<AlertResponseDto>> getAllAlerts() {

        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @GetMapping("/{alertId}")
    public ResponseEntity<AlertResponseDto> getAlertById(@PathVariable Long alertId) {

        return ResponseEntity.ok(alertService.getAlertById(alertId));
    }

    @PutMapping("/{alertId}")
    public ResponseEntity<AlertResponseDto> updateAlert(@PathVariable Long alertId, @Valid @RequestBody AlertRequestDto alertRequestDto) {

        return ResponseEntity.ok(alertService.updateAlert(alertId, alertRequestDto));
    }

    @DeleteMapping("/{alertId}")
    public ResponseEntity<String> deleteAlertById(@PathVariable Long alertId) {

        alertService.deleteAlertById(alertId);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource deleted successfully");
    }
}
