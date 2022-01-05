package com.jurassic.jurassiccrm.research.repository;

import com.jurassic.jurassiccrm.research.entity.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchesRepository extends
        JpaRepository<Research, Long>,
        CrudRepository<Research, Long> {
}
