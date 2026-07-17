package com.disaster.missionservice.repository;

import com.disaster.missionservice.entity.Missions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Missions, Long> {
}
