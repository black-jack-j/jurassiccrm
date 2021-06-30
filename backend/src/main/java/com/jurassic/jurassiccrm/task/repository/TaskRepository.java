package com.jurassic.jurassiccrm.task.repository;

import com.jurassic.jurassiccrm.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends
        CrudRepository<Task, Long>,
        JpaRepository<Task, Long> {
}
