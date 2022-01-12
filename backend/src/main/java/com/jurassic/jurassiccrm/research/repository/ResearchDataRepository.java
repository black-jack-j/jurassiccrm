package com.jurassic.jurassiccrm.research.repository;

import com.jurassic.jurassiccrm.research.entity.ResearchData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchDataRepository extends
        JpaRepository<ResearchData, Long>,
        CrudRepository<ResearchData, Long> {
    boolean existsByName(String name);
}
