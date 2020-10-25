package com.fresco.tournament.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.tournament.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
}