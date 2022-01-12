package com.jurassic.jurassiccrm.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jurassic.jurassiccrm.task.model.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private TaskType taskType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastUpdated;

    private Long taskPriorityId;

    private Long createdById;

    private Long lastUpdaterId;

    private Long assigneeId;

    private String description;

    private Map<String, Object> additionalParams = new HashMap<>();

}