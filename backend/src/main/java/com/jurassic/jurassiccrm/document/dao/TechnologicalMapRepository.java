package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.document.model.TechnologicalMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologicalMapRepository extends
        CrudRepository<TechnologicalMap, Long>,
        JpaRepository<TechnologicalMap, Long> {
    boolean existsByName(String name);
}
