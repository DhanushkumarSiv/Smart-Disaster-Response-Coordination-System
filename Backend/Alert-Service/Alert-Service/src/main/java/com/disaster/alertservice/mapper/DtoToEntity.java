package com.disaster.alertservice.mapper;

import com.disaster.alertservice.dto.AlertRequestDto;
import com.disaster.alertservice.entity.Alert;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DtoToEntity {

    Alert toAlert(AlertRequestDto alertRequestDto);

    void updateAlert(AlertRequestDto alertRequestDto, @MappingTarget Alert alert);
}
