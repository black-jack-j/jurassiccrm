package com.jurassic.jurassiccrm.task.dto;

import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(parent = Task.class)
@ToString(callSuper = true)
public class ResearchTaskDTO extends TaskTO {

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String purpose;

    public static ResearchTaskDTO fromTask(ResearchTask task) {
        ResearchTaskDTO dto = new ResearchTaskDTO();
        dto.setBaseFields(task);
        dto.setPurpose(task.getPurpose());
        return dto;
    }
}
