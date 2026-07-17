package com.disaster.alertservice.service;

import com.disaster.alertservice.dto.AlertRequestDto;
import com.disaster.alertservice.dto.AlertResponseDto;
import com.disaster.alertservice.entity.Alert;
import com.disaster.alertservice.exception.AlertNotFoundException;
import com.disaster.alertservice.mapper.DtoToEntity;
import com.disaster.alertservice.mapper.EntityToDto;
import com.disaster.alertservice.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private DtoToEntity dtoToEntity;

    @Autowired
    private EntityToDto entityToDto;


    public AlertResponseDto createAlert(AlertRequestDto alertRequestDto) {

        Alert alert = dtoToEntity.toAlert(alertRequestDto);
        alertRepository.save(alert);
        return entityToDto.toResponseDto(alert);
    }

    public AlertResponseDto updateAlert(Long alertId, AlertRequestDto alertRequestDto) {

        Alert alert = alertRepository.findById(alertId).
                orElseThrow(() -> new AlertNotFoundException("Alert not found."));

        dtoToEntity.updateAlert(alertRequestDto, alert);
        alertRepository.save(alert);
        return entityToDto.toResponseDto(alert);
    }

    public List<AlertResponseDto> getAllAlerts() {

        List<Alert> alerts = alertRepository.findAll();
        return alerts.stream()
                .map(alert -> entityToDto.toResponseDto(alert))
                .toList();
    }

    public AlertResponseDto getAlertById(Long alertId) {

        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new AlertNotFoundException("Alert not found."));

        return entityToDto.toResponseDto(alert);
    }

    public void deleteAlertById(Long alertId) {
        if(!alertRepository.existsById(alertId)) {
            throw new AlertNotFoundException("Alert not found.");
        }
        alertRepository.deleteById(alertId);
    }
}
