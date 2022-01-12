package com.jurassic.jurassiccrm.task.controller;

import com.jurassic.jurassiccrm.accesscontroll.entity.JurassicUserDetails;
import com.jurassic.jurassiccrm.accesscontroll.service.UserService;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.builder.exception.TaskBuildException;
import com.jurassic.jurassiccrm.task.dto.AssigneeDTO;
import com.jurassic.jurassiccrm.task.dto.ResearchTaskDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskTOValidator taskValidator;

    @Autowired
    private TaskBuilder taskBuilder;

    @Autowired
    private UserService userService;

    @PostMapping("/{taskType}")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @ResponseBody
    public ResponseEntity<TaskTO> createTask(@PathVariable TaskType taskType, @RequestBody TaskTO taskTO,
                                                    BindingResult bindingResult,
                                                    Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        JurassicUserDetails userDetails = (JurassicUserDetails) authentication.getPrincipal();
        try {
            taskTO.setId(null);
            taskService.createTask(userDetails.getUserInfo(), taskTO);
            return ResponseEntity.ok().build();
        } catch (TaskValidationException | CreateTaskException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @ResponseBody
    public ResponseEntity<TaskTO> updateTask(@PathVariable Long taskId, @RequestBody TaskTO taskUpdateTO,
                                             Authentication authentication) {
        JurassicUserDetails userDetails = (JurassicUserDetails) authentication.getPrincipal();
        try {
            taskValidator.validate(taskUpdateTO);
            Task taskUpdate = taskBuilder.buildEntityFromTO(taskUpdateTO);
            taskUpdate = taskService.updateTask(userDetails.getUserInfo(), taskUpdate);
            return ResponseEntity.ok(taskBuilder.buildTOFromEntity(taskUpdate));
        } catch (TaskValidationException | TaskBuildException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{taskId}/status/{taskState}")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @ResponseBody
    public ResponseEntity<TaskTO> changeState(@PathVariable Long taskId,
                                              @PathVariable TaskState taskState,
                                              Authentication authentication) {
        JurassicUserDetails userDetails = (JurassicUserDetails) authentication.getPrincipal();
        try {
            Task taskUpdate = taskService.updateTaskState(userDetails.getUserInfo(), taskId, taskState);
            return ResponseEntity.ok(taskBuilder.buildTOFromEntity(taskUpdate));
        } catch (IllegalTaskStateChangeException | TaskUpdateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public String tasksDashboard(Model model) {
        model.addAttribute("tasks", taskService.getAvailableTasks());
        return "/task/index";
    }

    @GetMapping("/create")
    public String createTask(Model model) {
        model.addAttribute("createTaskDTO", new ResearchTaskDTO());
        return "/task/create";
    }

    @ResponseBody
    @GetMapping("/type")
    public ResponseEntity<List<TaskType>> getTaskTypes() {
        return ResponseEntity.ok(Arrays.asList(TaskType.values().clone()));
    }

    @ResponseBody
    @GetMapping("/assignee")
    public ResponseEntity<List<AssigneeDTO>> getPossibleAssignees() {
        List<AssigneeDTO> possibleAssignees = taskService.getAvailableAssignees().stream()
                .map(user -> new AssigneeDTO(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(possibleAssignees);
    }

}
