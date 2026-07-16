package com.disaster.resourceservice.controller;

import com.disaster.resourceservice.dto.MappingRequestDto;
import com.disaster.resourceservice.dto.MappingResponseDto;
import com.disaster.resourceservice.service.MappingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resource/mapping")
public class MappingController {

    @Autowired
    private MappingService mappingService;

    @PostMapping
    public ResponseEntity<String> createMapping(@Valid @RequestBody MappingRequestDto mappingRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(mappingService.createMapping(mappingRequestDto));
    }

    @GetMapping("/{mappingId}")
    public ResponseEntity<MappingResponseDto> getMappingById(@PathVariable Long mappingId) {

        return ResponseEntity.ok(mappingService.getMappingById(mappingId));
    }

    @GetMapping
    public ResponseEntity<List<MappingResponseDto>> getAllMappings() {

        return ResponseEntity.ok(mappingService.getAllMappings());
    }

    @PutMapping("/{mappingId}")
    public ResponseEntity<MappingResponseDto> updateMapping(@PathVariable Long mappingId, @Valid @RequestBody MappingRequestDto mappingRequestDto) {

        return ResponseEntity.ok(mappingService.updateMapping(mappingId, mappingRequestDto));
    }

    @DeleteMapping("/{mappingId}")
    public ResponseEntity<String> deleteMapping(@PathVariable Long mappingId) {

        mappingService.deleteMapping(mappingId);

        return ResponseEntity.ok("Mapping deleted successfully");
    }
}
