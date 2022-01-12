package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.repository.DocumentRepository;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.dto.validation.TaskTOValidator;
import com.jurassic.jurassiccrm.task.dto.validation.TaskTOValidatorImpl;
import com.jurassic.jurassiccrm.task.model.Task;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = TaskService.class)
@MockBean(classes = {
        DocumentRepository.class,
        TaskBuilder.class,
        TaskTOValidatorImpl.class
})
@ActiveProfiles("test")
public class TaskServiceUnitTest {

    @Mock
    private Task task;

    private User lastUpdater;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Test
    public void testWhenTaskUpdated_thenLastUpdatedChanged() {
        taskService.updateTask(lastUpdater, task);

        verify(task, times(1)).setLastUpdated(any());
    }

    @Test
    public void testWhenTaskUpdated_thenLastUpdaterChanged() {
        taskService.updateTask(lastUpdater, task);

        verify(task, times(1)).setLastUpdater(eq(lastUpdater));
    }
}
