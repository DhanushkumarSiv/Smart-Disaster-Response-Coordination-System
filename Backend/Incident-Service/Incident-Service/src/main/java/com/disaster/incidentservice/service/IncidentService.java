package com.disaster.incidentservice.service;

import com.disaster.incidentservice.dto.IncidentRequestDto;
import com.disaster.incidentservice.dto.IncidentResponseDto;
import com.disaster.incidentservice.entity.Incident;
import com.disaster.incidentservice.exception.IncidentNotFoundException;
import com.disaster.incidentservice.mapper.DtoToEntityMapper;
import com.disaster.incidentservice.mapper.EntityToDtoMapper;
import com.disaster.incidentservice.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private DtoToEntityMapper entityMapper;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;


    public IncidentResponseDto createIncident(IncidentRequestDto incidentRequestDto) {

        Incident incident = entityMapper.toIncident(incidentRequestDto);
        Incident savedIncident = incidentRepository.save(incident);

        return entityToDtoMapper.toIncidentResponseDto(savedIncident);
    }

    public IncidentResponseDto getIncidentById(Long incidentId) {

        Incident incident = incidentRepository.findById(incidentId)
                .orElseThrow(() -> new IncidentNotFoundException("Incident Not Found."));

        return entityToDtoMapper.toIncidentResponseDto(incident);
    }

    public List<IncidentResponseDto> getAllIncident() {
        List<Incident> incidents = incidentRepository.findAll();
        List<IncidentResponseDto> incidentResponseDtoList = new ArrayList<>();
        for (Incident incident : incidents) {
            incidentResponseDtoList.add(entityToDtoMapper.toIncidentResponseDto(incident));
        }
        return incidentResponseDtoList;
    }

    public IncidentResponseDto updateIncident(Long incidentId, IncidentRequestDto incidentRequestDto) {

        Incident incident = incidentRepository.findById(incidentId)
                .orElseThrow(() -> new IncidentNotFoundException("Incident Not Found."));

        entityMapper.updateIncidentFromDto(incidentRequestDto, incident);

        Incident updatedIncident = incidentRepository.save(incident);
        return entityToDtoMapper.toIncidentResponseDto(updatedIncident);
    }

    public void deleteIncidentById(Long incidentId) {

        if(!incidentRepository.existsById(incidentId)) {
            throw new IncidentNotFoundException("Incident Not Found.");
        }
        incidentRepository.deleteById(incidentId);
    }

}
