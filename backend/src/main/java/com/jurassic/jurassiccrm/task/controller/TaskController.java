package com.jurassic.jurassiccrm.task.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.logging.model.LogActionType;
import com.jurassic.jurassiccrm.logging.service.LogService;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.dto.AssigneeDTO;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.model.exception.IllegalTaskStateChangeException;
import com.jurassic.jurassiccrm.task.model.state.TaskState;
import com.jurassic.jurassiccrm.task.service.TaskService;
import com.jurassic.jurassiccrm.task.service.exception.TaskUpdateException;
import com.jurassic.jurassiccrm.validation.ValidationResponseTO;
import com.jurassic.jurassiccrm.validation.groups.OnCreate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/task")
@Api(tags = "task")
@Validated
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final TaskBuilder taskBuilder;
    private final LogService logService;

    @Autowired
    public TaskController(TaskService taskService, TaskBuilder taskBuilder, LogService logService) {
        this.taskService = taskService;
        this.taskBuilder = taskBuilder;
        this.logService = logService;
    }

    @PostMapping("/{taskType}")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @ApiOperation(value = "createTask", nickname = "createTask", response = TaskTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "validation error", response = ValidationResponseTO.class),
            @ApiResponse(code = 200, message = "ok", response = TaskTO.class)
    })
    @ResponseBody
    @Validated(OnCreate.class)
    public ResponseEntity<? extends TaskTO> createTask(@PathVariable TaskType taskType, @Valid @RequestBody TaskTO taskTO,
                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        TaskTO createdTask = taskService.createTask(userDetails.getUserInfo(), taskTO);
        logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, taskType.getName() + " task", createdTask.getName());
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @ResponseBody
    @ApiOperation(value = "updateTask", nickname = "updateTask")
    public ResponseEntity<? extends TaskTO> updateTask(@PathVariable Long taskId, @RequestBody TaskTO taskUpdateTO,
                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        Task taskUpdate = taskBuilder.buildEntityFromTO(taskUpdateTO);
        taskUpdate = taskService.updateTask(taskId, userDetails.getUserInfo(), taskUpdate);
        logService.logCrudAction(userDetails.getUserInfo(), LogActionType.UPDATE,
                taskUpdate.getTaskType().getName() + " task", taskUpdate.getName());
        return ResponseEntity.ok(taskBuilder.buildTOFromEntity(taskUpdate));
    }

    @PatchMapping("/{taskId}/status/{taskState}")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @ResponseBody
    @ApiOperation(value = "changeStatus", nickname = "changeStatus")
    public ResponseEntity<TaskTO> changeState(@PathVariable Long taskId,
                                              @PathVariable TaskState taskState,
                                              @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
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
    @ApiOperation(value = "getTasks", nickname = "getTasks")
    public ResponseEntity<List<TaskTO>> getTasks() {
        List<TaskTO> tasks = taskService.getAvailableTasks().stream()
                .map(taskBuilder::buildTOFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }

    @ResponseBody
    @GetMapping("/{taskType}/{id}")
    @ApiOperation(value = "get task by id", nickname = "getTaskById")
    public ResponseEntity<TaskTO> getTaskById(@PathVariable("taskType") TaskType taskType,
                                              @PathVariable("id") Long id,
                                              @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        Task task = taskService.getTaskById(taskType, id, userDetails.getUserInfo());
        return ResponseEntity.ok(taskBuilder.buildTOFromEntity(task));
    }

    @ResponseBody
    @GetMapping("/type")
    @PreAuthorize("hasAnyRole('TASK_READER', 'ADMIN')")
    @ApiOperation(value = "getTaskTypes", nickname = "getTaskTypes")
    public ResponseEntity<List<TaskType>> getTaskTypes() {
        return ResponseEntity.ok(Arrays.asList(TaskType.values().clone()));
    }

    @ResponseBody
    @GetMapping("/assignee")
    @PreAuthorize("hasAnyRole('TASK_READER', 'ADMIN')")
    @ApiOperation(value = "getPossibleAssignees", nickname = "getPossibleAssignees")
    public ResponseEntity<List<AssigneeDTO>> getPossibleAssignees() {
        List<AssigneeDTO> possibleAssignees = taskService.getAvailableAssignees().stream()
                .map(user -> new AssigneeDTO(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(possibleAssignees);
    }

}
