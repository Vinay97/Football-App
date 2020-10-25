package com.fresco.tournament.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fresco.tournament.models.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}