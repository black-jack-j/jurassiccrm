package com.jurassic.jurassiccrm.task.dto;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class CreateTaskDTO {

    @NotBlank
    @Size(max = 128)
    @NotNull
    private String summary;

    @Nullable
    private Long assigneeId;

    @Nullable
    @Size(max = 255)
    private String description;

    public abstract String getTaskType();

}
