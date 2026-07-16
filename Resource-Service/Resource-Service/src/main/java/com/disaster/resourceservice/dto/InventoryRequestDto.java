package com.disaster.resourceservice.dto;

import com.disaster.resourceservice.entity.Status;
import lombok.Data;

@Data
public class InventoryRequestDto {

    private String location;

    private Double totalQuantity;

    private Double reservedQuantity;

    private Double reorderLevel;

    private Status status;

    private Long resourceId;
}
