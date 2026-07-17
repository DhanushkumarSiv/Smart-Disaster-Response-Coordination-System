package com.disaster.alertservice.mapper;


import com.disaster.alertservice.dto.AlertResponseDto;
import com.disaster.alertservice.entity.Alert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToDto {

    AlertResponseDto toResponseDto(Alert alert);
}
