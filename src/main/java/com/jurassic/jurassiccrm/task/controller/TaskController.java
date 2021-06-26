package com.jurassic.jurassiccrm.task.controller;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.task.dto.CreateTaskDTO;
import com.jurassic.jurassiccrm.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @PreAuthorize("hasRole('TASK_WRITER')")
    public String createTask(@ModelAttribute("createTaskDTO") @Valid CreateTaskDTO dto,
                             BindingResult bindingResult,
                             Model model,
                             Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "/task/create";
        }
        return "/task/index";
    }

    @GetMapping
    public String tasksDashboard(Model model) {
        model.addAttribute("tasks", new ArrayList<>());

        return "/task/index";
    }

    @GetMapping("/create")
    public String createTask(Model model) {
        Set<User> possibleAssignees = taskService.getAvailableAssignees();
        model.addAttribute("possibleAssignees", possibleAssignees);
        model.addAttribute("createTaskDTO", new CreateTaskDTO());
        model.addAttribute("taskTypes", Arrays.asList("RESEARCH", "ADMINISTRATION"));
        model.addAttribute("availableDocuments", taskService.getAvailableDocuments());
        return "/task/create";

    }

}
