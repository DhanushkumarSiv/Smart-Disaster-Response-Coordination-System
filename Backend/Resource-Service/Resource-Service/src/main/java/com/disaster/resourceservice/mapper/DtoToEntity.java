package com.disaster.resourceservice.mapper;

import com.disaster.resourceservice.dto.InventoryRequestDto;
import com.disaster.resourceservice.dto.ResourceRequestDto;
import com.disaster.resourceservice.entity.Inventory;
import com.disaster.resourceservice.entity.Resource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoToEntity {
    Resource toResource(ResourceRequestDto resourceRequestDto);

    Inventory toInventory(InventoryRequestDto inventoryRequestDto);


}
