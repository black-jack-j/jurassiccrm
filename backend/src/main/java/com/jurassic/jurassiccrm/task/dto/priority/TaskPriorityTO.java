package com.jurassic.jurassiccrm.task.dto.priority;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskPriorityTO {

    private Long id;
    private String name;

}
