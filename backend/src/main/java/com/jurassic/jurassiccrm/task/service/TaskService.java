package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.repository.DocumentMeta;
import com.jurassic.jurassiccrm.document.repository.DocumentRepository;
import com.jurassic.jurassiccrm.task.entity.Task;
import com.jurassic.jurassiccrm.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final DocumentRepository documentRepository;

    @Autowired
    public TaskService(
            UserRepository userRepository,
            TaskRepository taskRepository,
            DocumentRepository documentRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.documentRepository = documentRepository;
    }

    @Transactional
    public Task createTask(Task taskData, User author) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        taskData.setCreatedBy(author);
        taskData.setLastUpdater(author);
        taskData.setCreated(now);
        taskData.setLastUpdated(now);

        return taskRepository.save(taskData);
    }

    public List<Task> getAvailableTasks() {
        return taskRepository.findAll();
    }

    public Set<User> getAvailableAssignees() {
        return new HashSet<>(userRepository.findAll());
    }

    public Set<DocumentMeta> getAvailableDocuments() {
        return documentRepository.findAllBy();
    }

}
