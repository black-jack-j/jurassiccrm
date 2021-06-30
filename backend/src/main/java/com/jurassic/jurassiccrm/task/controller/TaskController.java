package com.jurassic.jurassiccrm.task.controller;

import com.jurassic.jurassiccrm.accesscontroll.entity.JurassicUserDetails;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.service.UserService;
import com.jurassic.jurassiccrm.task.dto.AssigneeDTO;
import com.jurassic.jurassiccrm.task.dto.CreateTaskDTO;
import com.jurassic.jurassiccrm.task.dto.IncubationTaskDTO;
import com.jurassic.jurassiccrm.task.dto.ResearchTaskDTO;
import com.jurassic.jurassiccrm.task.entity.Task;
import com.jurassic.jurassiccrm.task.entity.TaskType;
import com.jurassic.jurassiccrm.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/RESEARCH")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @ResponseBody
    public ResponseEntity<BindingResult> createTask(@RequestBody @Valid ResearchTaskDTO createTaskDTO,
                                     BindingResult bindingResult,
                                     Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        JurassicUserDetails userDetails = (JurassicUserDetails)authentication.getPrincipal();
        Task taskToCreate = new Task();
        User assignee = userService.getUserByIdOrThrowException(createTaskDTO.getAssigneeId());
        taskToCreate.setAssignee(assignee);
        taskToCreate.setDescription(createTaskDTO.getDescription());
        taskToCreate.setSummary(createTaskDTO.getSummary());
        taskToCreate.setTaskType(TaskType.valueOf(createTaskDTO.getTaskType()));
        taskService.createTask(taskToCreate, userDetails.getUserInfo());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/INCUBATION")
    @PreAuthorize("hasAnyRole('TASK_WRITER', 'ADMIN')")
    @ResponseBody
    public ResponseEntity<BindingResult> createTask(@RequestBody @Valid IncubationTaskDTO createTaskDTO,
                             BindingResult bindingResult,
                             Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        JurassicUserDetails userDetails = (JurassicUserDetails)authentication.getPrincipal();
        Task taskToCreate = new Task();
        User assignee = userService.getUserByIdOrThrowException(createTaskDTO.getAssigneeId());
        taskToCreate.setAssignee(assignee);
        taskToCreate.setDescription(createTaskDTO.getDescription());
        taskToCreate.setSummary(createTaskDTO.getSummary());
        taskToCreate.setTaskType(TaskType.valueOf(createTaskDTO.getTaskType()));
        taskService.createTask(taskToCreate, userDetails.getUserInfo());
        return ResponseEntity.ok().build();
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
