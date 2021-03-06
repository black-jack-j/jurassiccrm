package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.dao.DocumentRepository;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.model.exception.IllegalTaskStateChangeException;
import com.jurassic.jurassiccrm.task.model.state.TaskState;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.task.util.EntitiesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TaskService.class)
@MockBean(classes = {
        DocumentRepository.class,
        TaskBuilder.class,
        UserRepository.class,
        TaskPriorityRepository.class
})
@ActiveProfiles("test")
public class TaskServiceUnitTest {

    private final User lastUpdater = new User("user");

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Test
    public void testWhenValidNextState_thenTaskStateChanged() {
        Task task = EntitiesUtil.getTask("test", TaskType.INCUBATION);
        task.setId(1L);
        task.setStatus(TaskState.OPEN);

        when(taskRepository.findById(eq(1L))).thenReturn(Optional.of(task));

        Assertions.assertDoesNotThrow(() -> taskService.updateTaskState(lastUpdater, 1L, TaskState.IN_PROGRESS));
    }

    @Test
    public void testWhenInValidNextState_thenTaskStateNotChanged() {
        Task task = EntitiesUtil.getTask("test", TaskType.INCUBATION);
        task.setId(1L);
        task.setStatus(TaskState.RESOLVED);

        when(taskRepository.findById(eq(1L))).thenReturn(Optional.of(task));

        Assertions.assertThrows(IllegalTaskStateChangeException.class, () -> taskService.updateTaskState(lastUpdater, 1L, TaskState.IN_PROGRESS));
    }
}
