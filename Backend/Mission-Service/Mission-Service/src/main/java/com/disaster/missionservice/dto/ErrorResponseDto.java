package com.disaster.missionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private int status;

    private String error;

    private String message;

    private LocalDateTime timestamp;

    private String path;
}
