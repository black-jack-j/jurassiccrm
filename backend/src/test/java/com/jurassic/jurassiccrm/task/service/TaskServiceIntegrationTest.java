package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.dto.validation.TaskTOValidator;
import com.jurassic.jurassiccrm.task.dto.validation.TaskTOValidatorImpl;
import com.jurassic.jurassiccrm.task.dto.validation.exception.TaskValidationException;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.task.service.exception.CreateTaskException;
import com.jurassic.jurassiccrm.task.util.EntitiesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import({
        TaskService.class,
        TaskBuilder.class,
        TaskTOValidatorImpl.class
})
@ActiveProfiles("test")
public class TaskServiceIntegrationTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskBuilder taskBuilder;

    @Autowired
    private TaskTOValidator mockValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskPriorityRepository taskPriorityRepository;

    @Autowired
    private AviaryTypeRepository aviaryTypeRepository;

    @Autowired
    private DinosaurTypeRepository dinosaurTypeRepository;

    @Autowired
    private TaskRepository taskRepository;

    private User user;

    private Task existingTask;

    @BeforeEach
    private void setup() {
        user = EntitiesUtil.getUser("test", "test");
        user = userRepository.saveAndFlush(user);

        existingTask = EntitiesUtil.getTask("test", TaskType.AVIARY_CREATION);
        existingTask.setCreatedBy(user);
        existingTask.setLastUpdater(user);

        existingTask = taskRepository.saveAndFlush(existingTask);
    }

    @Test
    @DisplayName("Test that valid task is saved")
    public void testWhenTaskTOValid_thenTaskIsSaved() throws CreateTaskException, TaskValidationException {
        TaskTO taskTO = EntitiesUtil.getTaskTO("test", TaskType.INCUBATION);
        TaskTO createdTaskTO = taskService.createTask(user, taskTO);

        Assertions.assertTrue(taskRepository.findById(createdTaskTO.getId()).isPresent());
    }

    @Test
    @DisplayName("Test that invalid task is not saved")
    public void testWhenTaskTOInvalid_thenNoTaskIsSavedWithValidationException() {
        TaskTO taskTO = EntitiesUtil.getTaskTO("test", TaskType.INCUBATION);
        taskTO.setTaskType(null);

        Assertions.assertThrows(TaskValidationException.class, () -> taskService.createTask(user, taskTO));
    }

}
