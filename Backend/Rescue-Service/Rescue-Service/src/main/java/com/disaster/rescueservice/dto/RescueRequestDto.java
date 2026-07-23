package com.disaster.rescueservice.dto;

import com.disaster.rescueservice.entity.Department;
import com.disaster.rescueservice.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RescueRequestDto {

    @NotBlank(message = "Team Name field must be filled.")
    @Pattern(regexp = "^[A-Za-z0-9\\s._-]+$", message = "Enter a valid team name.")
    private String teamName;

    @NotBlank(message = "Location field must be filled.")
    private String location;

    @NotNull(message = "Status field must be selected.")
    private Status status;

    @NotNull(message = "Department field must be selected.")
    private Department department;
}
