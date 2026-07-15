package com.disaster.rescueservice.mapper;

import com.disaster.rescueservice.dto.RescueResponseDto;
import com.disaster.rescueservice.entity.Rescue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToDto {

    RescueResponseDto toResponseDto(Rescue rescue);
}
