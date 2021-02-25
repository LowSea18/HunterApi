package com.example.HunterApi.repository;

import com.example.HunterApi.model.Entity.HuntingClub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HuntingClubRepository extends JpaRepository<HuntingClub,Long> {
    Optional<HuntingClub> findByName(String name);
}
