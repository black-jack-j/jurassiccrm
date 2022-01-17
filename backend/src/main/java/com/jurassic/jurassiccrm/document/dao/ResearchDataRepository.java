package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.document.model.ResearchData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ResearchDataRepository extends
        JpaRepository<ResearchData, Long>,
        CrudRepository<ResearchData, Long> {
    boolean existsByName(String name);
}
