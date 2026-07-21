package com.disaster.missionservice.repository;

import com.disaster.missionservice.entity.Missions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Missions, Long> {

    List<Missions> findByEscalatedFalseAndCreatedAtBefore(LocalDateTime cutoff);
}
