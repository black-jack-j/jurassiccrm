package com.jurassic.jurassiccrm.task.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.logging.model.LogActionType;
import com.jurassic.jurassiccrm.logging.service.LogService;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.builder.exception.TaskBuildException;
import com.jurassic.jurassiccrm.task.dto.AssigneeDTO;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.dto.validation.TaskTOValidator;
import com.jurassic.jurassiccrm.task.dto.validation.exception.TaskValidationException;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.model.exception.IllegalTaskStateChangeException;
import com.jurassic.jurassiccrm.task.model.state.TaskState;
import com.jurassic.jurassiccrm.task.service.TaskService;
import com.jurassic.jurassiccrm.task.service.exception.CreateTaskException;
import com.jurassic.jurassiccrm.task.service.exception.TaskUpdateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/task")
@Tag(name = "task")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final TaskTOValidator taskValidator;
    private final TaskBuilder taskBuilder;
    private final LogService logService;

    @Autowired
    public TaskController(TaskService taskService, TaskTOValidator taskValidator, TaskBuilder taskBuilder, LogService logService) {
        this.taskService = taskService;
        this.taskValidator = taskValidator;
        this.taskBuilder = taskBuilder;
        this.logService = logService;
    }

    @PostMapping("/{taskType}")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @Operation(operationId = "createTask")
    @ResponseBody
    public ResponseEntity<TaskTO> createTask(@PathVariable TaskType taskType, @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody TaskTO taskTO,
                                             @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            taskTO.setId(null);
            TaskTO createdTask = taskService.createTask(userDetails.getUserInfo(), taskTO);
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, taskType.getName() + " task", createdTask.getName());
            return ResponseEntity.ok(createdTask);
        } catch (TaskValidationException | CreateTaskException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @ResponseBody
    @Operation(operationId = "updateTask")
    public ResponseEntity<TaskTO> updateTask(@PathVariable Long taskId, @RequestBody TaskTO taskUpdateTO,
                                             @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            taskValidator.validate(taskUpdateTO);
            Task taskUpdate = taskBuilder.buildEntityFromTO(taskUpdateTO);
            taskUpdate = taskService.updateTask(userDetails.getUserInfo(), taskUpdate);
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.UPDATE,
                    taskUpdate.getTaskType().getName() + " task", taskUpdate.getName());
            return ResponseEntity.ok(taskBuilder.buildTOFromEntity(taskUpdate));
        } catch (TaskValidationException | TaskBuildException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{taskId}/status/{taskState}")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @ResponseBody
    @Operation(operationId = "changeStatus")
    public ResponseEntity<TaskTO> changeState(@PathVariable Long taskId,
                                              @PathVariable TaskState taskState,
                                              @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            Task taskUpdate = taskService.updateTaskState(userDetails.getUserInfo(), taskId, taskState);
            return ResponseEntity.ok(taskBuilder.buildTOFromEntity(taskUpdate));
        } catch (IllegalTaskStateChangeException | TaskUpdateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('TASK_READER', 'ADMIN')")
    @ResponseBody
    @Operation(operationId = "getTasks")
    public ResponseEntity<List<TaskTO>> getTasks() {
        List<TaskTO> tasks = taskService.getAvailableTasks().stream()
                .map(taskBuilder::buildTOFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }

    @ResponseBody
    @GetMapping("/type")
    @PreAuthorize("hasAnyRole('TASK_READER', 'ADMIN')")
    @Operation(operationId = "getTaskTypes")
    public ResponseEntity<List<TaskType>> getTaskTypes() {
        return ResponseEntity.ok(Arrays.asList(TaskType.values().clone()));
    }

    @ResponseBody
    @GetMapping("/assignee")
    @PreAuthorize("hasAnyRole('TASK_READER', 'ADMIN')")
    @Operation(operationId = "getPossibleAssignees")
    public ResponseEntity<List<AssigneeDTO>> getPossibleAssignees() {
        List<AssigneeDTO> possibleAssignees = taskService.getAvailableAssignees().stream()
                .map(user -> new AssigneeDTO(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(possibleAssignees);
    }

}
