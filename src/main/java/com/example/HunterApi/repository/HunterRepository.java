package com.example.HunterApi.repository;

import com.example.HunterApi.model.Entity.Hunter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HunterRepository extends JpaRepository<Hunter, Long> {
    Optional<Hunter> findByNameAndSurname(String name ,String surname);
    Optional<Hunter> findByName(String name);
    Optional<Hunter> findBySurname(String surname);

}
