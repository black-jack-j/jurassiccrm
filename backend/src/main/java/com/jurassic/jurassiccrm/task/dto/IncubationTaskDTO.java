package com.jurassic.jurassiccrm.task.dto;


import com.jurassic.jurassiccrm.task.entity.TaskType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class IncubationTaskDTO extends CreateTaskDTO {

    @NotBlank
    @NotNull
    private String species;

    @Override
    public String getTaskType() {
        return TaskType.INCUBATION.name();
    }
}