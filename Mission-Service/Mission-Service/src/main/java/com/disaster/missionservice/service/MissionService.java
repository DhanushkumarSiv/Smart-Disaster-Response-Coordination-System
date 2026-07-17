package com.disaster.missionservice.service;

import com.disaster.missionservice.client.IncidentClient;
import com.disaster.missionservice.client.RescueTeamClient;
import com.disaster.missionservice.dto.MissionRequestDto;
import com.disaster.missionservice.dto.MissionResponseDto;
import com.disaster.missionservice.dto.MissionTeamRequestDto;
import com.disaster.missionservice.dto.incidentDto.IncidentDto;
import com.disaster.missionservice.dto.incidentDto.IncidentSeverity;
import com.disaster.missionservice.dto.rescueTeamDto.RescueTeamStatus;
import com.disaster.missionservice.entity.MissionStatus;
import com.disaster.missionservice.entity.Priority;
import com.disaster.missionservice.entity.TeamStatus;
import com.disaster.missionservice.exception.IncidentNotFoundException;
import com.disaster.missionservice.mapper.DisasterMapper;
import com.disaster.missionservice.repository.MissionRepository;
import com.disaster.missionservice.repository.MissionResourceRepository;
import com.disaster.missionservice.repository.MissionTeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private MissionTeamsRepository missionTeamsRepository;

    @Autowired
    private MissionResourceRepository missionResourceRepository;

    @Autowired
    private IncidentClient incidentClient;

    @Autowired
    private RescueTeamClient rescueTeamClient;

    @Autowired
    private DisasterMapper disasterMapper;


    public Mono<MissionResponseDto> createMission() {

        return incidentClient.getIncident()

                .filter( incidentDto -> incidentDto.getSeverity() == IncidentSeverity.HIGH)
                .next()

                .switchIfEmpty(
                        incidentClient.getIncident()
                                .filter(incidentDto -> incidentDto.getSeverity() == IncidentSeverity.MEDIUM)
                                .next()
                )
                .switchIfEmpty(
                        incidentClient.getIncident()
                                .filter(incidentDto -> incidentDto.getSeverity() == IncidentSeverity.LOW)
                                .next()
                )
                .switchIfEmpty(Mono.error(new IncidentNotFoundException("No incidents Found.")))

                .flatMap(incidentDto ->  {
                    MissionRequestDto missionRequestDto = new MissionRequestDto();
                    missionRequestDto.setIncidentId(incidentDto.getIncidentId());
                    missionRequestDto.setTitle("Mission for" + incidentDto.getDisasterName());////maybe error/////
                    missionRequestDto.setDescription(incidentDto.getDescription());
                    missionRequestDto.setLocation(incidentDto.getLocation());
                    missionRequestDto.setPriority(Priority.valueOf(incidentDto.getSeverity().name()));
                    missionRequestDto.setMissionStatus(MissionStatus.CREATED);
                    missionRequestDto.setEscalated(false);
                    missionRequestDto.setCreatedAt(LocalDateTime.now());

                    return assignRescueTeam(incidentDto);
                });
    }

    private Mono<MissionResponseDto> assignRescueTeam(IncidentDto incidentDto) {

        return rescueTeamClient.getRescueTeam()

                .filter(rescueTeamDto ->
                        rescueTeamDto.getDepartment() == disasterMapper.mapDepartment(incidentDto.getDisasterName())
                )
                .filter(rescueTeamDto -> rescueTeamDto.getStatus() == RescueTeamStatus.AVAILABLE)

                .flatMap(rescueTeamDto -> {
                    MissionTeamRequestDto missionTeamRequestDto = new MissionTeamRequestDto();

                    missionTeamRequestDto.setRescueTeamId(rescueTeamDto.getTeamId());
                    missionTeamRequestDto.setAssignedAt(LocalDateTime.now());
                    missionTeamRequestDto.setTeamStatus(TeamStatus.ASSIGNED);
                });
    }
}
