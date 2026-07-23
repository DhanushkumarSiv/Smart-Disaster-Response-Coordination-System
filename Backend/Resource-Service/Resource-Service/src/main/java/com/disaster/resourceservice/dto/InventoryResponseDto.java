package com.disaster.resourceservice.dto;

import com.disaster.resourceservice.entity.Status;
import lombok.Data;

@Data
public class InventoryResponseDto {
    private Long inventoryId;

    private String location;

    private Double totalQuantity;

    private Double reservedQuantity;

    private Double availableQuantity;

    private Double reorderLevel;

    private Status status;
}
