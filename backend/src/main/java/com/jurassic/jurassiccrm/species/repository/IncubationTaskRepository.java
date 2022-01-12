package com.jurassic.jurassiccrm.species.repository;

import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubationTaskRepository extends
        JpaRepository<IncubationTask, Long>,
        CrudRepository<IncubationTask, Long> {
}
