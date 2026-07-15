package com.disaster.rescueservice.mapper;

import com.disaster.rescueservice.dto.RescueRequestDto;
import com.disaster.rescueservice.entity.Rescue;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DtoToEntity {

    Rescue toRescue(RescueRequestDto rescueRequestDto);

    void updateRescueFromDto(RescueRequestDto dto, @MappingTarget Rescue entity);
}
