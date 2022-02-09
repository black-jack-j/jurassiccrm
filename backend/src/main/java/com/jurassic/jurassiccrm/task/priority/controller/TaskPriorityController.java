package com.jurassic.jurassiccrm.task.priority.controller;

import com.jurassic.jurassiccrm.task.builder.TaskPriorityBuilder;
import com.jurassic.jurassiccrm.task.dto.priority.TaskPriorityTO;
import com.jurassic.jurassiccrm.task.priority.service.TaskPriorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "task priority", tags = "task")
@RequestMapping("/api/task/priority")
public class TaskPriorityController {

    private final TaskPriorityService taskPriorityService;

    @Autowired
    public TaskPriorityController(TaskPriorityService taskPriorityService) {
        this.taskPriorityService = taskPriorityService;
    }

    @ResponseBody
    @GetMapping()
    @PreAuthorize("hasAnyRole('TASK_READER', 'ADMIN')")
    @ApiOperation(value = "get available priorities", nickname = "getPriorities")
    public ResponseEntity<List<TaskPriorityTO>> getPriorities() {
        List<TaskPriorityTO> taskPriorities = taskPriorityService.getAllPriorities().stream()
                .map(TaskPriorityBuilder::fromEntity).collect(Collectors.toList());

        return ResponseEntity.ok(taskPriorities);
    }

}
