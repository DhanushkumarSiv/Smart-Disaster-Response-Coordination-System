package com.disaster.incidentservice.mapper;

import com.disaster.incidentservice.dto.IncidentResponseDto;
import com.disaster.incidentservice.entity.Incident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToDtoMapper {

    IncidentResponseDto toIncidentResponseDto(Incident incident);
}
