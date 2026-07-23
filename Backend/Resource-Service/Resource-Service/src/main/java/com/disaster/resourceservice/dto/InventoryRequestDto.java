package com.disaster.resourceservice.dto;

import com.disaster.resourceservice.entity.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InventoryRequestDto {

    @NotBlank(message = "Resource location field must be filled.")
    private String location;

    @NotBlank(message = "Resource quantity field must be filled.")
    private Double totalQuantity;

    @NotBlank(message = "Resource reserved quantity field must be filled.")
    private Double reservedQuantity;

    @NotBlank(message = "Resource re-order field must be filled.")
    private Double reorderLevel;

    @NotBlank(message = "Resource status field must be filled.")
    private Status status;

    @NotBlank(message = "Resource Id field must be filled.")
    private Long resourceId;
}
