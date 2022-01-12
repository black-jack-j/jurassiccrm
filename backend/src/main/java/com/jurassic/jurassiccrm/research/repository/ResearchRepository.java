package com.jurassic.jurassiccrm.research.repository;

import com.jurassic.jurassiccrm.research.entity.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResearchRepository extends
        JpaRepository<Research, Long>,
        CrudRepository<Research, Long> {

    Optional<Research> findByName(String name);
}
