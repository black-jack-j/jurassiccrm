package com.jurassic.jurassiccrm.task.dto;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateTaskDTO {

    @NotBlank
    @Max(128)
    private String summary;

    @Nullable
    private Long assigneeId;

    @Nullable
    @Max(255)
    private String description;

    @NotBlank
    @NotNull
    @Max(128)
    private String taskType;

    @Nullable
    private Long attachedDocumentId;
}
