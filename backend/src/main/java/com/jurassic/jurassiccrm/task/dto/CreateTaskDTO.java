package com.jurassic.jurassiccrm.task.dto;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateTaskDTO {

    @NotBlank
    @Size(max = 128)
    @NotNull
    private String status;

    @Nullable
    private Long assigneeId;

    @Nullable
    @Size(max = 255)
    private String description;

}
