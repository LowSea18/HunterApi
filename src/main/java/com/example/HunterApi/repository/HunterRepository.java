package com.example.HunterApi.repository;

import com.example.HunterApi.model.Hunter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HunterRepository extends JpaRepository<Hunter, Long> {
    Optional<Hunter> findByNameAndSurname(String name ,String surname);
    Optional<Hunter> findBySurname(String surname);

}
