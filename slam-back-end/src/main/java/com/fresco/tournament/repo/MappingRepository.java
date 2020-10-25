package com.fresco.tournament.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.tournament.models.Mapping;

public interface MappingRepository extends JpaRepository<Mapping, Long> {
}