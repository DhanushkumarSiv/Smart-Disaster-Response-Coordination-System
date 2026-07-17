package com.disaster.missionservice.repository;

import com.disaster.missionservice.entity.MissionResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionResourceRepository extends JpaRepository<MissionResource, Long> {
}
