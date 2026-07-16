package com.disaster.resourceservice.dto;

import com.disaster.resourceservice.entity.Category;
import lombok.Data;


@Data
public class ResourceRequestDto {

    private String resourceCode;

    private String name;

    private String description;

    private Category category;

    private String unitOfMeasure;

    private Boolean isActive;

}
