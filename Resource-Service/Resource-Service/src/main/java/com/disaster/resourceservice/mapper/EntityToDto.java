package com.disaster.resourceservice.mapper;

import com.disaster.resourceservice.dto.*;
import com.disaster.resourceservice.entity.Inventory;
import com.disaster.resourceservice.entity.Mapping;
import com.disaster.resourceservice.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntityToDto {

    ResourceResponseDto toResourceResponseDto(Resource resource);

    void updateResourceFromDto(ResourceRequestDto resourceRequestDto, @MappingTarget Resource resource);

    InventoryResponseDto toInventoryResponseDto(Inventory inventory);

    void updateInventoryFromDto(InventoryRequestDto inventoryRequestDto, @MappingTarget Inventory inventory);

    MappingResponseDto toMappingResponseDto(Mapping mapping);

    void updateMappingFromDto(MappingRequestDto mappingRequestDto, @MappingTarget Mapping mapping);
}
