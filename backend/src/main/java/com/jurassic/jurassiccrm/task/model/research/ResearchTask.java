package com.jurassic.jurassiccrm.task.model.research;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class ResearchTask extends Task {

    public ResearchTask() {
        this.setTaskType(TaskType.RESEARCH);
    }

    public ResearchTask(String name, User creator) {
        super(name, creator);
        this.setTaskType(TaskType.RESEARCH);
    }
}
