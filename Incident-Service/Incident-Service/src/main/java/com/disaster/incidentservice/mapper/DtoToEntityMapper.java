package com.disaster.incidentservice.mapper;

import com.disaster.incidentservice.dto.IncidentRequestDto;
import com.disaster.incidentservice.entity.Incident;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DtoToEntityMapper {

    Incident toIncident(IncidentRequestDto incidentRequestDto);

    void updateIncidentFromDto(IncidentRequestDto dto, @MappingTarget Incident entity);
}
