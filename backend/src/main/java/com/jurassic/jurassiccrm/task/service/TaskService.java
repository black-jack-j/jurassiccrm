package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.RoleRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.RoleService;
import com.jurassic.jurassiccrm.document.repository.DocumentMeta;
import com.jurassic.jurassiccrm.document.repository.DocumentRepository;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
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
    public <T extends Task> T createTask(T taskData, User author) {
        taskData.setCreatedBy(author);
        taskData.setLastUpdater(author);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        taskData.setCreated(currentTime);
        taskData.setLastUpdated(currentTime);
        return taskRepository.save(taskData);

    }

    public List<Task> getAvailableTasks() {
        return taskRepository.findAll();
    }

    public List<User> getAvailableAssignees() {
        return userRepository.findAll();
    }

    public Set<DocumentMeta> getAvailableDocuments() {
        return documentRepository.findAllBy();
    }

}
