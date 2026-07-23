package com.disaster.rescueservice.repository;

import com.disaster.rescueservice.entity.Rescue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RescueRepository extends JpaRepository<Rescue, Long> {
}
