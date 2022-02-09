package com.jurassic.jurassiccrm.task.priority.service;

import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskPriorityService {

    private final TaskPriorityRepository taskPriorityRepository;

    @Autowired
    public TaskPriorityService(TaskPriorityRepository taskPriorityRepository) {
        this.taskPriorityRepository = taskPriorityRepository;
    }

    public List<TaskPriority> getAllPriorities() {
        return taskPriorityRepository.findAll();
    }
}
