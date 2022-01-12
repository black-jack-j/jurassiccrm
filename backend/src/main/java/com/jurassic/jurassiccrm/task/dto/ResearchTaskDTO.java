package com.jurassic.jurassiccrm.task.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ResearchTaskDTO extends TaskTO {

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String purpose;
}
