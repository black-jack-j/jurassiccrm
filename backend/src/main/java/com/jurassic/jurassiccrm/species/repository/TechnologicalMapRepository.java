package com.jurassic.jurassiccrm.species.repository;

import com.jurassic.jurassiccrm.species.entity.TechnologicalMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologicalMapRepository extends
        CrudRepository<TechnologicalMap, Long>,
        JpaRepository<TechnologicalMap, Long> {
    boolean existsByName(String name);
}
