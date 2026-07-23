package com.disaster.incidentservice.dto;

import com.disaster.incidentservice.entity.Disaster;
import com.disaster.incidentservice.entity.Severity;
import com.disaster.incidentservice.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class IncidentRequestDto {

    @NotBlank(message = "Name field must be filled.")
    @Pattern(regexp = "^[A-Za-z\\s.]+$", message = "Enter a valid name.")
    private String reporterName;

    @NotBlank(message = "Mobile Number field must be filled.")
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Enter a valid mobile number.")
    private String reporterMobileNumber;

    @NotBlank(message = "Location field must be filled.")
    private String location;

    @NotNull(message = "Disaster Name field must be selected.")
    private Disaster disasterName;

    private String description;

    @NotNull(message = "Severity field must be selected.")
    private Severity severity;

    @NotNull(message = "Status field must be selected.")
    private Status status;

}
