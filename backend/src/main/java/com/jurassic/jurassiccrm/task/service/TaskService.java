package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.exception.IllegalTaskStateChangeException;
import com.jurassic.jurassiccrm.task.model.state.TaskState;
import com.jurassic.jurassiccrm.task.service.exception.TaskUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final TaskBuilder taskBuilder;

    @Autowired
    public TaskService(
            UserRepository userRepository,
            TaskRepository taskRepository,
            TaskBuilder taskBuilder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.taskBuilder = taskBuilder;
    }

    @Transactional
    public TaskTO createTask(User author, TaskTO taskTO) {
        Task task = taskBuilder.buildEntityFromTO(taskTO);

        Task createdTask = createTask(author, task);

        return taskBuilder.buildTOFromEntity(createdTask);

    }

    @Transactional
    public Task createTask(User author, Task task) {
        task.setCreatedBy(author);
        task.setLastUpdater(author);
        Instant now = Instant.now();
        task.setCreated(now);
        task.setLastUpdated(now);

        return taskRepository.save(task);
    }

    @Transactional(rollbackOn = IllegalTaskStateChangeException.class)
    public Task updateTask(User updater, Task updatedTask) {
        updatedTask.updateTask(updater);

        return taskRepository.save(updatedTask);
    }

    @Transactional(rollbackOn = {IllegalTaskStateChangeException.class})
    public Task updateTaskState(User updater, Long taskId, TaskState newTaskState) throws IllegalTaskStateChangeException, TaskUpdateException {
        Task taskToUpdate = taskRepository.findById(taskId).orElseThrow(TaskUpdateException::new);
        taskToUpdate.updateTask(updater);

        taskToUpdate.getTaskStateChanger(newTaskState).execute();
        return taskRepository.save(taskToUpdate);
    }


    public List<Task> getAvailableTasks() {
        return taskRepository.findAll();
    }

    public Set<User> getAvailableAssignees() {
        return new HashSet<>(userRepository.findAll());
    }

}
