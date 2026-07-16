package com.disaster.resourceservice.controller;

import com.disaster.resourceservice.dto.InventoryRequestDto;
import com.disaster.resourceservice.dto.InventoryResponseDto;
import com.disaster.resourceservice.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resource/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<String> createInventory(@Valid @RequestBody InventoryRequestDto inventoryRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createInventory(inventoryRequestDto));
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponseDto> getInventoryById(@PathVariable Long inventoryId) {

        return ResponseEntity.ok(inventoryService.getInventoryById(inventoryId));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponseDto>> getAllInventory() {

        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponseDto> updateInventory(@PathVariable Long inventoryId, @Valid @RequestBody InventoryRequestDto inventoryRequestDto) {

        return ResponseEntity.ok(inventoryService.updateInventory(inventoryId, inventoryRequestDto));
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long inventoryId) {

        inventoryService.deleteInventoryById(inventoryId);

        return ResponseEntity.ok("Inventory deleted successfully");
    }
}
