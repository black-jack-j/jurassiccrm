package com.jurassic.jurassiccrm.task.model.incubation;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class IncubationTask extends Task {

    @ManyToOne
    private DinosaurType dinosaurType;

    public IncubationTask() {
        this.setTaskType(TaskType.INCUBATION);
    }

    public IncubationTask(String name, User creator) {
        super(name, creator);
        this.setTaskType(TaskType.INCUBATION);
    }
}
