package com.disaster.resourceservice.dto;

import com.disaster.resourceservice.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class ResourceRequestDto {

    @NotBlank(message = "Resource code field must be filled.")
    @Pattern(regexp = "^RES[0-9]+$", message = "Enter a valid resource code.")
    private String resourceCode;

    @NotBlank(message = "Resource name field must be filled.")
    private String name;

    @NotBlank(message = "Resource description field must be filled.")
    private String description;

    @NotBlank(message = "Resource category field must be filled.")
    private Category category;

    @NotBlank(message = "Resource unit of measure field must be filled.")
    private String unitOfMeasure;

    @NotBlank(message = "Resource availability field must be filled.")
    private Boolean isActive;

}
