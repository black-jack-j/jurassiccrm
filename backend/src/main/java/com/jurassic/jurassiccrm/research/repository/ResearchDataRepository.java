package com.jurassic.jurassiccrm.research.repository;

import com.jurassic.jurassiccrm.research.entity.Research;
import com.jurassic.jurassiccrm.research.entity.ResearchData;
import com.jurassic.jurassiccrm.research.entity.ResearchTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResearchDataRepository extends
        JpaRepository<ResearchData, Long>,
        CrudRepository<ResearchData, Long> {
}
