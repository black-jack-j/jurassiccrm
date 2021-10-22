package com.jurassic.jurassiccrm.task.dto;

import com.jurassic.jurassiccrm.task.entity.TaskType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ResearchTaskDTO extends CreateTaskDTO {

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String purpose;

    @Override
    public String getTaskType() {
        return TaskType.RESEARCH.name();
    }
}