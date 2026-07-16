package com.disaster.resourceservice.service;

import com.disaster.resourceservice.dto.MappingRequestDto;
import com.disaster.resourceservice.dto.MappingResponseDto;
import com.disaster.resourceservice.entity.Mapping;
import com.disaster.resourceservice.entity.Resource;
import com.disaster.resourceservice.exception.ResourceNotFoundException;
import com.disaster.resourceservice.mapper.DtoToEntity;
import com.disaster.resourceservice.mapper.EntityToDto;
import com.disaster.resourceservice.repository.MappingRepository;
import com.disaster.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingService {

    @Autowired
    private MappingRepository mappingRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private DtoToEntity dtoToEntity;

    @Autowired
    private EntityToDto entityToDto;

    public String createMapping(MappingRequestDto mappingRequestDto) {

        Resource resource = resourceRepository.findById(mappingRequestDto.getResourceId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        Mapping mapping = dtoToEntity.toMapping(mappingRequestDto);
        mapping.setResource(resource);
        mappingRepository.save(mapping);

        return "Mapping created";
    }

    public MappingResponseDto getMappingById(Long mappingId) {
        Mapping mapping = mappingRepository.findById(mappingId)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));

        return entityToDto.toMappingResponseDto(mapping);
    }

    public List<MappingResponseDto> getAllMappings() {
        List<Mapping> mappings = mappingRepository.findAll();

        return mappings.stream()
                .map(mapping ->entityToDto.toMappingResponseDto(mapping))
                .toList();
    }

    public void deleteMapping(Long mappingId) {

        Mapping mapping = mappingRepository.findById(mappingId)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));

        mappingRepository.delete(mapping);
    }

    public MappingResponseDto updateMapping(Long mappingId, MappingRequestDto mappingRequestDto) {

        Mapping mapping = mappingRepository.findById(mappingId)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));

        Resource resource = resourceRepository.findById(mappingRequestDto.getResourceId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        entityToDto.updateMappingFromDto(mappingRequestDto, mapping);
        mapping.setResource(resource);
        Mapping updatedMapping = mappingRepository.save(mapping);

        return entityToDto.toMappingResponseDto(updatedMapping);
    }
}
