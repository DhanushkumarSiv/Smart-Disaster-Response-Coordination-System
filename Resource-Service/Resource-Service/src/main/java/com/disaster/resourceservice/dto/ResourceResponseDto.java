package com.disaster.resourceservice.dto;

import com.disaster.resourceservice.entity.Category;
import com.disaster.resourceservice.entity.Inventory;
import lombok.Data;

import java.util.List;

@Data
public class ResourceResponseDto {

    private Long resourceId;

    private String resourceCode;

    private String name;

    private String description;

    private Category category;

    private String unitOfMeasure;

    private Boolean isActive;

    private InventoryResponseDto inventories;

}
