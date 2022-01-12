package com.jurassic.jurassiccrm.research.repository;

import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchTaskRepository extends
        JpaRepository<ResearchTask, Long>,
        CrudRepository<ResearchTask, Long> {
}
