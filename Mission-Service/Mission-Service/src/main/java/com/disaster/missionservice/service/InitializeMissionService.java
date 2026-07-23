package com.disaster.missionservice.service;

import com.disaster.missionservice.client.IncidentClient;
import com.disaster.missionservice.client.RescueTeamClient;
import com.disaster.missionservice.client.ResourceClient;
import com.disaster.missionservice.dto.incidentDto.IncidentDto;
import com.disaster.missionservice.dto.incidentDto.IncidentSeverity;
import com.disaster.missionservice.dto.missionDto.*;
import com.disaster.missionservice.dto.rescueTeamDto.RescueTeamDto;
import com.disaster.missionservice.dto.rescueTeamDto.RescueTeamStatus;
import com.disaster.missionservice.entity.*;
import com.disaster.missionservice.exception.IncidentNotFoundException;
import com.disaster.missionservice.exception.NoAvailableTeamException;
import com.disaster.missionservice.exception.ResourceNotFoundException;
import com.disaster.missionservice.mapper.*;
import com.disaster.missionservice.repository.MissionRepository;
import com.disaster.missionservice.repository.MissionResourceRepository;
import com.disaster.missionservice.repository.MissionTeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InitializeMissionService {

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
    private ResourceClient resourceClient;

    @Autowired
    private DisasterMapper disasterMapper;

    @Autowired
    private DtoToEntity dtoToEntity;

    @Autowired
    private EntityToDto entityToDto;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private DtoToDto dtoToDto;

    public Mono<MissionCreateResponseDto> initializeMission() {

        List<Long> assignedIncidentIds = missionRepository.findAll().stream()
                .map(Missions::getIncidentId)
                .toList();

        return incidentClient.getIncident()
                .filter(incidentDto -> !assignedIncidentIds.contains(incidentDto.getIncidentId()))
                .filter(incidentDto -> incidentDto.getSeverity() == IncidentSeverity.HIGH)
                .next()
                .switchIfEmpty(
                        incidentClient.getIncident()
                                .filter(incidentDto -> !assignedIncidentIds.contains(incidentDto.getIncidentId()))
                                .filter(incidentDto -> incidentDto.getSeverity() == IncidentSeverity.MEDIUM)
                                .next()
                )
                .switchIfEmpty(
                        incidentClient.getIncident()
                                .filter(incidentDto -> !assignedIncidentIds.contains(incidentDto.getIncidentId()))
                                .filter(incidentDto -> incidentDto.getSeverity() == IncidentSeverity.LOW)
                                .next()
                )
                .switchIfEmpty(Mono.error(new IncidentNotFoundException("No incidents Found.")))
                .flatMap(incidentDto ->  {
                    MissionRequestDto missionRequestDto = new MissionRequestDto();
                    missionRequestDto.setIncidentId(incidentDto.getIncidentId());
                    missionRequestDto.setTitle("Mission for " + DisasterName.valueOf(incidentDto.getDisasterName().name()) +" Disaster");
                    missionRequestDto.setDisasterName(DisasterName.valueOf(incidentDto.getDisasterName().name()));
                    missionRequestDto.setDescription(incidentDto.getDescription());
                    missionRequestDto.setLocation(incidentDto.getLocation());
                    missionRequestDto.setPriority(Priority.valueOf(incidentDto.getSeverity().name()));
                    missionRequestDto.setMissionStatus(MissionStatus.CREATED);
                    missionRequestDto.setEscalated(false);
                    missionRequestDto.setCreatedAt(LocalDateTime.now());
                    Missions mission = missionRepository.save(dtoToEntity.toMissions(missionRequestDto));

                    return assignRescueTeam(incidentDto, mission);
                });
    }

    private Mono<MissionCreateResponseDto> assignRescueTeam(IncidentDto incidentDto, Missions mission) {

        return rescueTeamClient.getRescueTeam()
                .filter(rescueTeamDto ->
                        rescueTeamDto.getDepartment() == disasterMapper.mapDepartment(incidentDto.getDisasterName())
                )
                .filter(rescueTeamDto -> rescueTeamDto.getStatus() == RescueTeamStatus.AVAILABLE)
                .collectList()
                .flatMap(teams -> {
                    if (teams.isEmpty()) {
                        return Mono.error(new NoAvailableTeamException("No AVAILABLE rescue team found"));
                    }

                    RescueTeamDto selectedTeam = teams.get(0);
                    return Mono.just(selectedTeam);
                })
                .flatMap(rescueTeamDto -> {
                    MissionTeamRequestDto missionTeamRequestDto = new MissionTeamRequestDto();

                    missionTeamRequestDto.setRescueTeamId(rescueTeamDto.getTeamId());
                    missionTeamRequestDto.setAssignedAt(LocalDateTime.now());
                    missionTeamRequestDto.setMissionId(mission.getMissionId());
                    missionTeamRequestDto.setMissionTeamName(rescueTeamDto.getTeamName());
                    MissionTeams missionTeam = dtoToEntity.toMissionTeams(missionTeamRequestDto);
                    missionTeam.setMission(mission);
                    missionTeamsRepository.save(missionTeam);
                    MissionTeamsResponseDto missionTeamsResponseDto = dtoToDto.toMissionTeamsResponseDto(missionTeamRequestDto);

                    RescueTeamDto rescueTeamDto2 = new RescueTeamDto();
                    rescueTeamDto2.setStatus(RescueTeamStatus.BUSY);
                    rescueTeamDto2.setTeamId(rescueTeamDto.getTeamId());
                    rescueTeamDto2.setLocation(rescueTeamDto.getLocation());
                    rescueTeamDto2.setTeamName(rescueTeamDto.getTeamName());
                    rescueTeamDto2.setDepartment(rescueTeamDto.getDepartment());

                    return rescueTeamClient.updateTeamStatus(rescueTeamDto.getTeamId(), rescueTeamDto2)
                            .then(assignResources(mission));
                });
    }

    private Mono<MissionCreateResponseDto> assignResources(Missions mission) {

        return resourceClient.getResources()
                .filter(resourceDto ->
                        resourceMapper.resourceMapping(mission.getDisasterName())
                                .contains(resourceDto.getName())
                )
                .next()
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No resources found for disaster type: " + mission.getDisasterName())))
                .flatMap(resourceDto -> {
                    MissionResourceRequestDto missionResourceRequestDto = new MissionResourceRequestDto();
                    missionResourceRequestDto.setResourceId(resourceDto.getResourceId());
                    missionResourceRequestDto.setQuantity(5.0);
                    missionResourceRequestDto.setResourceStatus(ResourceStatus.RECEIVED);
                    missionResourceRequestDto.setMissionId(mission.getMissionId());
                    MissionResource missionResource = dtoToEntity.toMissionResource(missionResourceRequestDto);
                    missionResource.setMission(mission);
                    missionResourceRepository.save(missionResource);
                    entityToDto.toMissionResourceResponseDto(missionResource);

                    MissionCreateResponseDto missionCreateResponseDto = new MissionCreateResponseDto();
                    missionCreateResponseDto.setMissionId(mission.getMissionId());
                    missionCreateResponseDto.setIncidentId(mission.getIncidentId());
                    missionCreateResponseDto.setTitle(mission.getTitle());
                    missionCreateResponseDto.setDisasterName(mission.getDisasterName());
                    missionCreateResponseDto.setDescription(mission.getDescription());
                    missionCreateResponseDto.setLocation(mission.getLocation());
                    missionCreateResponseDto.setPriority(mission.getPriority());
                    missionCreateResponseDto.setMissionStatus(MissionStatus.CREATED);
                    missionCreateResponseDto.setCreatedAt(mission.getCreatedAt());

                    return Mono.just(missionCreateResponseDto);
                });
    }
}
