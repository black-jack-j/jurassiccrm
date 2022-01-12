package com.jurassic.jurassiccrm.task.priority.dao;

import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface TaskPriorityRepository extends Repository<TaskPriority, Long>, CrudRepository<TaskPriority, Long> {
}
