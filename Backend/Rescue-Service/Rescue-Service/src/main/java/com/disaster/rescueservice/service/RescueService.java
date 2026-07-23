package com.disaster.rescueservice.service;

import com.disaster.rescueservice.dto.RescueRequestDto;
import com.disaster.rescueservice.dto.RescueResponseDto;
import com.disaster.rescueservice.entity.Rescue;
import com.disaster.rescueservice.exception.TeamNotFoundException;
import com.disaster.rescueservice.mapper.DtoToEntity;
import com.disaster.rescueservice.mapper.EntityToDto;
import com.disaster.rescueservice.repository.RescueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RescueService {

    @Autowired
    private RescueRepository rescueRepository;

    @Autowired
    private DtoToEntity dtoToEntity;

    @Autowired
    private EntityToDto entityToDto;

    public RescueResponseDto createRescueTeam(RescueRequestDto rescueRequestDto) {

        Rescue rescue = dtoToEntity.toRescue(rescueRequestDto);
        Rescue savedRescue = rescueRepository.save(rescue);

        return entityToDto.toResponseDto(rescue);
    }

    public RescueResponseDto getRescueTeamById(Long rescueId) {

        Rescue rescue = rescueRepository.findById(rescueId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found."));

        return entityToDto.toResponseDto(rescue);
    }

    public List<RescueResponseDto> getAllRescueTeams() {

        List<Rescue> rescueList = rescueRepository.findAll();
        List<RescueResponseDto> rescueResponseDtoList = new ArrayList<>();
        for(Rescue rescue : rescueList) {
            rescueResponseDtoList.add(entityToDto.toResponseDto(rescue));
        }
        return rescueResponseDtoList;
    }

    public RescueResponseDto updateRescueTeam(Long rescueId, RescueRequestDto rescueRequestDto) {

        Rescue rescue = rescueRepository.findById(rescueId)
                .orElseThrow(() -> new TeamNotFoundException("Rescue team not found."));

        dtoToEntity.updateRescueFromDto(rescueRequestDto, rescue);

        Rescue updatedRescue = rescueRepository.save(rescue);
        return entityToDto.toResponseDto(updatedRescue);
    }

    public void deleteRescueTeamById(Long rescueId) {

        if(!rescueRepository.existsById(rescueId)) {
            throw new  TeamNotFoundException("Rescue team not found.");
        }
        rescueRepository.deleteById(rescueId);
    }
}
