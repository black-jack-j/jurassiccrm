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
import java.util.Set;

@Service
public class TaskService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DocumentRepository documentRepository;

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

    public Set<User> getAvailableAssignees() {
        return userRepository.findUsersByRolesName("ROLE_TASK_READER");
    }

    public Set<DocumentMeta> getAvailableDocuments() {
        return documentRepository.findAllBy();
    }

}
