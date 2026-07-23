package com.disaster.rescueservice.dto;

import com.disaster.rescueservice.entity.Department;
import com.disaster.rescueservice.entity.Status;
import lombok.Data;

@Data
public class RescueResponseDto {

    private Long teamId;

    private String teamName;

    private String location;

    private Status status;

    private Department department;
}
