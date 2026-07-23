package com.disaster.missionservice.repository;

import com.disaster.missionservice.entity.MissionTeams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MissionTeamsRepository extends JpaRepository<MissionTeams, Long> {

    Optional<MissionTeams> findByMission_MissionId(Long missionId);

}
