package com.jurassic.jurassiccrm.task.model.aviary;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class CreateAviaryTask extends Task {

    @ManyToOne
    @JoinColumn(name = "aviary_type_id")
    private AviaryType aviaryType;

    private Long aviarySquare;

    public CreateAviaryTask() {
        this.setTaskType(TaskType.AVIARY_CREATION);
    }

    public CreateAviaryTask(String name, User creator) {
        super(name, creator);
        this.setTaskType(TaskType.AVIARY_CREATION);
    }
}
