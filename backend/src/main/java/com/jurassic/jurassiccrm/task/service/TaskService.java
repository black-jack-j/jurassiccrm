package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.RoleRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.RoleService;
import com.jurassic.jurassiccrm.document.repository.DocumentMeta;
import com.jurassic.jurassiccrm.document.repository.DocumentRepository;
import com.jurassic.jurassiccrm.task.entity.Task;
import com.jurassic.jurassiccrm.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    private final RoleRepository roleRepository;

    private final RoleService roleService;

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final DocumentRepository documentRepository;

    @Autowired
    public TaskService(RoleRepository roleRepository,
                       RoleService roleService,
                       UserRepository userRepository,
                       TaskRepository taskRepository,
                       DocumentRepository documentRepository) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.documentRepository = documentRepository;
    }

    @Transactional
    public Task createTask(Task taskData, User author) {

        Task savedTask = taskRepository.save(taskData);

        Role taskAuthor = roleService.getBasicRole("TASK_AUTHOR");

        taskAuthor.addResource(savedTask);

        if (author.addRole(taskAuthor)) {
            userRepository.save(author);
        }

        return savedTask;

    }

    public List<Task> getAvailableTasks() {
        return taskRepository.findAll();
    }

    public Set<User> getAvailableAssignees() {
        return userRepository.findUsersByRolesName("ROLE_TASK_READER");
    }

}
