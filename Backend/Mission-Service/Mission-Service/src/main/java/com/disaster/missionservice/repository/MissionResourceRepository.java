package com.disaster.missionservice.repository;

import com.disaster.missionservice.entity.MissionResource;
import com.disaster.missionservice.entity.MissionTeams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MissionResourceRepository extends JpaRepository<MissionResource, Long> {

    Optional<MissionResource> findByMission_MissionId(Long missionId);
}
