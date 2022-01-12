package com.jurassic.jurassiccrm.research.dao;

import com.jurassic.jurassiccrm.research.model.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResearchRepository extends
        JpaRepository<Research, Long>,
        CrudRepository<Research, Long> {

    Optional<Research> findByName(String name);
}
