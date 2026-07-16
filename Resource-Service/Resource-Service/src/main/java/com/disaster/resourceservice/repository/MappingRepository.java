package com.disaster.resourceservice.repository;

import com.disaster.resourceservice.entity.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MappingRepository extends JpaRepository<Mapping, Long> {
}
