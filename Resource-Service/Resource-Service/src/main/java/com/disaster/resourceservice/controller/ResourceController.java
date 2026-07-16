package com.disaster.resourceservice.controller;

import com.disaster.resourceservice.dto.ResourceRequestDto;
import com.disaster.resourceservice.dto.ResourceResponseDto;
import com.disaster.resourceservice.service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping
    public ResponseEntity<String> createResource(@Valid @RequestBody ResourceRequestDto resourceRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(resourceService.createResource(resourceRequestDto));
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<ResourceResponseDto> getResourceById(@PathVariable Long resourceId) {

        return ResponseEntity.ok(resourceService.getResourceById(resourceId));
    }

    @GetMapping
    public ResponseEntity<List<ResourceResponseDto>> getAllResources() {

        return ResponseEntity.ok(resourceService.getAllResources());
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<ResourceResponseDto> updateResource(@PathVariable Long resourceId, @Valid @RequestBody ResourceRequestDto resourceRequestDto) {

        return ResponseEntity.ok(resourceService.updateResource(resourceId, resourceRequestDto));
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<String> deleteResource(@PathVariable Long resourceId) {

        resourceService.deleteResourceById(resourceId);

        return ResponseEntity.ok("Resource deleted successfully");
    }
}
