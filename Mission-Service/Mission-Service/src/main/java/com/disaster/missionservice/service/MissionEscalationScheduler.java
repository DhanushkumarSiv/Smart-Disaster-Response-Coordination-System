package com.disaster.missionservice.service;

import com.disaster.missionservice.client.AlertClient;
import com.disaster.missionservice.dto.alertDto.AlertDto;
import com.disaster.missionservice.entity.MissionStatus;
import com.disaster.missionservice.entity.Missions;
import com.disaster.missionservice.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MissionEscalationScheduler {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private AlertClient alertClient;

    // Checks every minute
    @Scheduled(fixedRate = 60000)
    public void escalateUnresolvedMissions() {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(30);
        List<Missions> unresolvedMissions = missionRepository.findByEscalatedFalseAndCreatedAtBefore(cutoff);

        for (Missions mission : unresolvedMissions) {
            if (mission.getMissionStatus() != MissionStatus.COMPLETED &&
                mission.getMissionStatus() != MissionStatus.FAILED &&
                mission.getMissionStatus() != MissionStatus.CANCELLED) {

                mission.setEscalated(true);
                missionRepository.save(mission);

                // Construct alert Request matching Alert-Service's AlertRequestDto
                AlertDto alertDto = new AlertDto();
                alertDto.setAlertType("MISSION_STUCK");
                alertDto.setSourceService("MISSION");
                alertDto.setSourceId(mission.getMissionId());
                alertDto.setSeverity("ESCALATED");
                alertDto.setMessage("Mission " + mission.getMissionId() + " has been unresolved for 30 minutes and is escalated.");
                alertDto.setIsAcknowledged(false);

                try {
                    alertClient.sendAlert(alertDto);
                } catch (Exception e) {
                    // Prevent failure to reach Alert Service from interrupting scheduler loop
                }
            }
        }
    }
}
