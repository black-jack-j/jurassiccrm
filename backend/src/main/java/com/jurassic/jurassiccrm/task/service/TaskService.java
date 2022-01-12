package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.repository.DocumentRepository;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.builder.exception.TaskBuildException;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.dto.validation.TaskTOValidator;
import com.jurassic.jurassiccrm.task.dto.validation.exception.TaskValidationException;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.exception.IllegalTaskStateChangeException;
import com.jurassic.jurassiccrm.task.model.state.TaskState;
import com.jurassic.jurassiccrm.task.service.exception.CreateTaskException;
import com.jurassic.jurassiccrm.task.service.exception.TaskUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final DocumentRepository documentRepository;

    private final TaskBuilder taskBuilder;

    private final TaskTOValidator taskTOValidator;

    @Autowired
    public TaskService(
            UserRepository userRepository,
            TaskRepository taskRepository,
            DocumentRepository documentRepository,
            TaskBuilder taskBuilder,
            TaskTOValidator taskTOValidator) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.documentRepository = documentRepository;
        this.taskBuilder = taskBuilder;
        this.taskTOValidator = taskTOValidator;
    }

    @Transactional(rollbackOn = {TaskBuildException.class, CreateTaskException.class})
    public TaskTO createTask(User author, TaskTO taskTO) throws CreateTaskException, TaskValidationException {
        checkTaskIsValidOrThrowException(taskTO);

        Task task = getTaskFromTOorThrowException(taskTO);

        Task createdTask = createTask(author, task);

        return taskBuilder.buildTOFromEntity(createdTask);

    }

    @Transactional
    public Task createTask(User author, Task task) {
        task.setCreatedBy(author);
        task.setLastUpdater(author);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        task.setCreated(currentTime);
        task.setLastUpdated(currentTime);

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

    private void checkTaskIsValidOrThrowException(TaskTO taskTO) throws TaskValidationException {
        isValid(taskTO);
    }

    private Task getTaskFromTOorThrowException(TaskTO taskTO) throws CreateTaskException {
        try {
            return taskBuilder.buildEntityFromTO(taskTO);
        } catch (TaskBuildException e) {
            throw new CreateTaskException();
        }
    }

    private void isValid(TaskTO taskTO) throws TaskValidationException {
        taskTOValidator.validate(taskTO);
    }

    public List<Task> getAvailableTasks() {
        return taskRepository.findAll();
    }

    public Set<User> getAvailableAssignees() {
        return new HashSet<>(userRepository.findAll());
    }

}
