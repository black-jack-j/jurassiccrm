package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.common.model.EntityNotExistException;
import com.jurassic.jurassiccrm.common.model.UnauthorizedAccessException;
import com.jurassic.jurassiccrm.document.dao.DocumentRepository;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.model.exception.IllegalTaskStateChangeException;
import com.jurassic.jurassiccrm.task.model.state.TaskState;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.task.service.exception.TaskUpdateException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final DocumentRepository documentRepository;
    private final TaskPriorityRepository taskPriorityRepository;

    private final TaskBuilder taskBuilder;

    private final Validator validator;

    @Autowired
    public TaskService(
            UserRepository userRepository,
            TaskRepository taskRepository,
            DocumentRepository documentRepository,
            TaskPriorityRepository taskPriorityRepository,
            TaskBuilder taskBuilder,
            Validator validator) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.documentRepository = documentRepository;
        this.taskPriorityRepository = taskPriorityRepository;
        this.taskBuilder = taskBuilder;
        this.validator = validator;
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

    @Transactional
    public Task getTaskById(TaskType taskType, Long id, User requester) {
        checkReadPermissions(taskType, requester);
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotExistException(id));
    }

    @Transactional(rollbackOn = IllegalTaskStateChangeException.class)
    public Task updateTask(Long id, User updater, Task updatedTask) {
        val task = taskRepository.findById(id).orElseThrow(() -> new EntityNotExistException(id));
        val priority = updatedTask.getPriority();
        val updatedPriority = taskPriorityRepository.findById(priority.getId())
                .orElseThrow(() -> new EntityNotExistException(priority.getId()));
        val assignee = updatedTask.getAssignee();
        val updatedAssignee = userRepository.findById(assignee.getId())
                .orElseThrow(() -> new EntityNotExistException(id));

        task.setName(updatedTask.getName());
        task.setPriority(updatedPriority);
        task.setDescription(updatedTask.getDescription());
        task.setAssignee(updatedAssignee);
        task.updateTask(updater);
        return taskRepository.save(task);
    }

    @Transactional(rollbackOn = {IllegalTaskStateChangeException.class})
    public Task updateTaskState(User updater, Long taskId, TaskState newTaskState) throws IllegalTaskStateChangeException, TaskUpdateException {
        Task taskToUpdate = taskRepository.findById(taskId).orElseThrow(TaskUpdateException::new);
        taskToUpdate.updateTask(updater);

        taskToUpdate.getTaskStateChanger(newTaskState).execute();
        return taskRepository.save(taskToUpdate);
    }

    private void checkReadPermissions(TaskType taskType, User requester) {
        if (requester.canReadTasks()) return;
        switch (taskType) {
            case RESEARCH: {
                if (!requester.canReadTasks()) throw new UnauthorizedAccessException();
                break;
            }
            case INCUBATION: {
                if (!requester.canReadIncubationTasks()) throw new UnauthorizedAccessException();
                break;
            }
            case AVIARY_CREATION: {
                if (!requester.canReadAviaryCreationTasks()) throw new UnauthorizedAccessException();
                break;
            }
        }
    }

    public List<Task> getAvailableTasks() {
        return taskRepository.findAll();
    }

    public Set<User> getAvailableAssignees() {
        return new HashSet<>(userRepository.findAll());
    }

}
