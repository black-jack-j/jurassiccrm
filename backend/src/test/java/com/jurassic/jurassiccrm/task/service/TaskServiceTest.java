package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.RoleRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.RoleService;
import com.jurassic.jurassiccrm.document.repository.DocumentRepository;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = TaskService.class)
@MockBean(classes = {
        DocumentRepository.class,
        UserRepository.class,
        RoleRepository.class,
        RoleService.class
})
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    private User dumbUser;

    @BeforeEach
    private void setup() {
        dumbUser = new User();
        Mockito.when(taskRepository.save(any())).then((Answer<Task>) invocationOnMock -> invocationOnMock.getArgument(0));
    }

    @Test
    @DisplayName("Test that task is created with creator specified")
    public void testWhenTaskCreated_thenItHasCreator() {
        Task task = new ResearchTask();
        task = taskService.createTask(task, dumbUser);
        Assertions.assertEquals(dumbUser, task.getCreatedBy());
    }

    @Test
    @DisplayName("Test that task creator and its last updater are same for newly created task")
    public void testWhenTaskCreated_thenItsCreatorAndLastUpdaterAreSame() {
        Task task = new ResearchTask();
        task = taskService.createTask(task, dumbUser);
        Assertions.assertEquals(task.getCreatedBy(), task.getLastUpdater(),
                "Creator and last updater should be same when task created");
    }

    @Test
    @DisplayName("Test that 'created' and 'lastUpdated' fields are same for newly created task")
    public void testWhenTaskCreated_thenItsCreatedAndLastUpdatedFieldsAreSame() {
        Task task = new ResearchTask();
        task = taskService.createTask(task, dumbUser);
        Timestamp created = task.getCreated();
        Timestamp lastUpdated = task.getLastUpdated();
        Assertions.assertEquals(created.getTime(), lastUpdated.getTime(),
                "'created' and 'lastUpdated' timestamps should be same when task created");
    }

}
