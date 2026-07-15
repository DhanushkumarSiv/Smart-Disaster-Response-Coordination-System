package com.disaster.incidentservice.mapper;

import com.disaster.incidentservice.dto.IncidentRequestDto;
import com.disaster.incidentservice.dto.IncidentResponseDto;
import com.disaster.incidentservice.entity.Incident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface DtoToDtoMapper {

    IncidentResponseDto toResponseDto(IncidentRequestDto incidentRequestDto);
}
