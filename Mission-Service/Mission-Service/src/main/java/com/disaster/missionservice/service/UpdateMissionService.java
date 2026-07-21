package com.disaster.missionservice.service;

import com.disaster.missionservice.client.IncidentClient;
import com.disaster.missionservice.client.RescueTeamClient;
import com.disaster.missionservice.dto.incidentDto.IncidentDto;
import com.disaster.missionservice.dto.incidentDto.IncidentStatus;
import com.disaster.missionservice.dto.missionDto.MissionResponseDto;
import com.disaster.missionservice.dto.missionDto.UpdateMissionRequestDto;
import com.disaster.missionservice.dto.rescueTeamDto.RescueTeamDto;
import com.disaster.missionservice.dto.rescueTeamDto.RescueTeamStatus;
import com.disaster.missionservice.entity.*;
import com.disaster.missionservice.exception.MissionNotFoundException;
import com.disaster.missionservice.mapper.EntityToDto;
import com.disaster.missionservice.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateMissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private EntityToDto entityToDto;

    @Autowired
    private RescueTeamClient rescueTeamClient;

    @Autowired
    private IncidentClient incidentClient;

    public MissionResponseDto startMission(Long missionId, UpdateMissionRequestDto update) {

        Missions mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionNotFoundException("Mission not found"));

        if (update.getMissionStatus() == MissionStatus.IN_PROGRESS) {
            mission.setMissionStatus(MissionStatus.IN_PROGRESS);
            mission.setStartedAt(LocalDateTime.now());
        } else if (update.getMissionStatus() == MissionStatus.COMPLETED) {
            mission.setMissionStatus(MissionStatus.COMPLETED);
            mission.setCompletedAt(LocalDateTime.now());
            releaseMissionAssets(mission);
            resolveIncident(mission.getIncidentId());
        } else if (update.getMissionStatus() == MissionStatus.CANCELLED) {
            mission.setMissionStatus(MissionStatus.CANCELLED);
            mission.setCompletedAt(LocalDateTime.now());
            releaseMissionAssets(mission);
        } else if (update.getMissionStatus() == MissionStatus.FAILED) {
            mission.setMissionStatus(MissionStatus.FAILED);
            mission.setCompletedAt(LocalDateTime.now());
            releaseMissionAssets(mission);
        }
        missionRepository.save(mission);

        return entityToDto.toMissionResponseDto(mission);
    }

    private void releaseMissionAssets(Missions mission) {
        MissionTeams missionTeam = mission.getMissionTeams();
        if (missionTeam != null) {
            missionTeam.setReleasedAt(LocalDateTime.now());
            try {
                RescueTeamDto rescueTeamDto = rescueTeamClient.getRescueTeamById(missionTeam.getRescueTeamId()).block();
                if (rescueTeamDto != null) {
                    rescueTeamDto.setStatus(RescueTeamStatus.AVAILABLE);
                    rescueTeamClient.updateTeamStatus(missionTeam.getRescueTeamId(), rescueTeamDto).block();
                }
            } catch (Exception e) {
                    System.out.println(e.getMessage());

            }
        }
        if (mission.getMissionResources() != null) {
            mission.getMissionResources().setResourceStatus(ResourceStatus.RETURNED);
        }
    }

    private void resolveIncident(Long incidentId) {
        if (incidentId == null) {
            return;
        }
        try {

            IncidentDto incidentDto = incidentClient.getIncidentById(incidentId).block();
            if (incidentDto != null) {
                incidentDto.setStatus(IncidentStatus.RESOLVED);
                incidentClient.updateIncident(incidentId, incidentDto).block();
            }
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }
}
