package com.jurassic.jurassiccrm.task.builder;

import com.jurassic.jurassiccrm.task.dto.priority.TaskPriorityTO;
import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;

public class TaskPriorityBuilder {

    public static TaskPriorityTO fromEntity(TaskPriority taskPriority) {
        return TaskPriorityTO.builder()
                .id(taskPriority.getId())
                .name(taskPriority.getName())
                .build();
    }

}
