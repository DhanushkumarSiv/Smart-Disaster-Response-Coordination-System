package com.disaster.resourceservice.service;

import com.disaster.resourceservice.dto.ResourceRequestDto;
import com.disaster.resourceservice.dto.ResourceResponseDto;
import com.disaster.resourceservice.entity.Resource;
import com.disaster.resourceservice.exception.ResourceNotFoundException;
import com.disaster.resourceservice.mapper.DtoToEntity;
import com.disaster.resourceservice.mapper.EntityToDto;
import com.disaster.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private DtoToEntity dtoToEntity;

    @Autowired
    private EntityToDto entityToDto;

    public String createResource(ResourceRequestDto resourceRequestDto) {
        resourceRepository.save(dtoToEntity.toResource(resourceRequestDto));
        return "Resource Created";
    }

    public List<ResourceResponseDto> getAllResources() {
        List<Resource> resources = resourceRepository.findAll();

        return resources.stream()
                .map(resource -> entityToDto.toResourceResponseDto(resource))
                .toList();
    }

    public ResourceResponseDto getResourceById(Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        return entityToDto.toResourceResponseDto(resource);
    }

    public void deleteResourceById(Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        resourceRepository.delete(resource);
    }

    public ResourceResponseDto updateResource(Long resourceId, ResourceRequestDto resourceRequestDto) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        entityToDto.updateResourceFromDto(resourceRequestDto,resource);
        Resource updatedResource = resourceRepository.save(resource);

        return entityToDto.toResourceResponseDto(updatedResource);
    }
}
