package com.disaster.resourceservice.service;

import com.disaster.resourceservice.dto.InventoryRequestDto;
import com.disaster.resourceservice.dto.InventoryResponseDto;
import com.disaster.resourceservice.entity.Inventory;
import com.disaster.resourceservice.entity.Resource;
import com.disaster.resourceservice.exception.ResourceNotFoundException;
import com.disaster.resourceservice.mapper.DtoToEntity;
import com.disaster.resourceservice.mapper.EntityToDto;
import com.disaster.resourceservice.repository.InventoryRepository;
import com.disaster.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private DtoToEntity dtoToEntity;

    @Autowired
    private EntityToDto entityToDto;

    public String createInventory(InventoryRequestDto inventoryRequestDto) {

        Resource resource = resourceRepository.findById(inventoryRequestDto.getResourceId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        Inventory inventory = dtoToEntity.toInventory(inventoryRequestDto);
        inventory.setResource(resource);

        Double total = inventoryRequestDto.getTotalQuantity();
        Double reserved = inventoryRequestDto.getReservedQuantity();

        if (total == null || reserved == null) {
            throw new IllegalArgumentException("Quantity values cannot be null");
        }

        if (reserved > total) {
            throw new IllegalArgumentException("Reserved quantity cannot exceed total quantity");
        }

        inventory.setAvailableQuantity(total - reserved);

        inventoryRepository.save(inventory);
        return "Inventory created";
    }

    public List<InventoryResponseDto> getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();

        return inventories.stream()
                .map(inventory ->entityToDto.toInventoryResponseDto(inventory))
                .toList();
    }

    public InventoryResponseDto getInventoryById(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

        return entityToDto.toInventoryResponseDto(inventory);
    }


    public InventoryResponseDto updateInventory(Long inventoryId, InventoryRequestDto inventoryRequestDto) {

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

        Resource resource = resourceRepository.findById(inventoryRequestDto.getResourceId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        entityToDto.updateInventoryFromDto(inventoryRequestDto, inventory);
        inventory.setResource(resource);
        Inventory updatedInventory = inventoryRepository.save(inventory);

        return entityToDto.toInventoryResponseDto(updatedInventory);
    }


    public void deleteInventoryById(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

        inventoryRepository.delete(inventory);
    }

}
