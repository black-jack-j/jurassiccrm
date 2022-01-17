package com.jurassic.jurassiccrm.task.dto.validation;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.dto.validation.exception.TaskValidationException;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TaskTOValidatorImpl.class)
@ActiveProfiles("test")
public class TaskTOValidatorTest {

    private static final Long EXISTING_USER_ID = 1L;
    private static final Long EXISTING_TASK_ID = 1L;
    private static final Long EXISTING_AVIARY_TYPE_ID = 1L;
    private static final Long EXISTING_DINOSAUR_TYPE_ID = 1L;
    private static final Long EXISTING_TASK_PRIORITY_ID = 1L;

    @Autowired
    private TaskTOValidator validator;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TaskPriorityRepository taskPriorityRepository;

    @MockBean
    private AviaryTypeRepository aviaryTypeRepository;

    @MockBean
    private DinosaurTypeRepository dinosaurTypeRepository;

    @MockBean
    private TaskRepository taskRepository;

    @Mock
    private Task task;

    @Mock
    private User user;

    @Mock
    private TaskPriority taskPriority;

    @Mock
    private AviaryType aviaryType;

    @Mock
    private DinosaurType dinosaurType;

    @BeforeEach
    private void setup() {
        mockRepoToReturnSomethingOnSpecifiedIdOnly(task, EXISTING_TASK_ID, taskRepository);
        mockRepoToReturnSomethingOnSpecifiedIdOnly(user, EXISTING_USER_ID, userRepository);
        mockRepoToReturnSomethingOnSpecifiedIdOnly(taskPriority, EXISTING_TASK_PRIORITY_ID, taskPriorityRepository);
        mockRepoToReturnSomethingOnSpecifiedIdOnly(aviaryType, EXISTING_AVIARY_TYPE_ID, aviaryTypeRepository);
        mockRepoToReturnSomethingOnSpecifiedIdOnly(dinosaurType, EXISTING_DINOSAUR_TYPE_ID, dinosaurTypeRepository);

        when(task.getId()).thenReturn(EXISTING_TASK_ID);
        when(task.getTaskType()).thenReturn(TaskType.INCUBATION);
        when(user.getId()).thenReturn(EXISTING_USER_ID);
        when(taskPriority.getId()).thenReturn(EXISTING_TASK_PRIORITY_ID);
        when(aviaryType.getId()).thenReturn(EXISTING_AVIARY_TYPE_ID);
        when(dinosaurType.getId()).thenReturn(EXISTING_DINOSAUR_TYPE_ID);
    }

    private <T> void mockRepoToReturnSomethingOnSpecifiedIdOnly(T reply, Long id, CrudRepository<T, Long> repository) {
        when(repository.findById(eq(id))).thenReturn(Optional.of(reply));
        when(repository.findById(AdditionalMatchers.not(eq(id)))).thenReturn(Optional.empty());
    }

    private TaskTO getValidTaskTOOfType(TaskType taskType) {
        when(task.getTaskType()).thenReturn(taskType);

        return TaskTO.builder()
                .additionalParams(new HashMap<>())
                .taskType(taskType)
                .name("test")
                .description("test").build();
    }

    @Test
    public void testValidTaskTOIsValidated() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        Assertions.assertDoesNotThrow(() -> validator.validate(taskTO));
    }

    @Test
    public void testTaskWithoutNameIsInvalid() {
        TaskTO task = getValidTaskTOOfType(TaskType.INCUBATION);

        task.setName(null);

        Assertions.assertThrows(TaskValidationException.class, () -> validator.validate(task));
    }

    @Test
    public void testTaskWithoutTypeIsInvalid() {
        TaskTO task = getValidTaskTOOfType(TaskType.INCUBATION);

        task.setTaskType(null);

        Assertions.assertThrows(TaskValidationException.class, () -> validator.validate(task));
    }

    @Test
    public void testTaskWithInvalidCreatedByIsInvalid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setCreatedById(0L);

        Assertions.assertThrows(TaskValidationException.class, () -> validator.validate(taskTO));
    }

    @Test
    public void testTaskWithExistingCreatedByIdIsValid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setCreatedById(EXISTING_USER_ID);

        Assertions.assertDoesNotThrow(() -> validator.validate(taskTO));
    }

    @Test
    public void testTaskTOWithInvalidTaskIdIsInvalid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setId(0L);

        Assertions.assertThrows(TaskValidationException.class, () -> validator.validate(taskTO));
    }

    @Test
    public void testTaskTOWithValidTaskIdIsValid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setId(EXISTING_TASK_ID);

        Assertions.assertDoesNotThrow(() -> validator.validate(taskTO));
    }

    @Test
    public void testTaskTOWithInvalidTaskPriorityIdIsInvalid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setTaskPriorityId(0L);

        Assertions.assertThrows(TaskValidationException.class, () -> validator.validate(taskTO));
    }

    @Test
    public void testTaskTOWithValidTaskPriorityIdIsValid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setTaskPriorityId(EXISTING_TASK_PRIORITY_ID);

        Assertions.assertDoesNotThrow(() -> validator.validate(taskTO));
    }

    @Test
    public void testAviaryCreationTaskTOWithInvalidAviaryTypeIdIsInvalid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.AVIARY_CREATION);

        taskTO.getAdditionalParams().put("aviaryTypeId", 0L);

        Assertions.assertThrows(TaskValidationException.class, () -> validator.validate(taskTO));
    }

    @Test
    public void testAviaryCreationTaskTOWithValidAviaryTypeIdIsValid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.AVIARY_CREATION);

        taskTO.getAdditionalParams().put("aviaryTypeId", EXISTING_AVIARY_TYPE_ID);

        Assertions.assertDoesNotThrow(() -> validator.validate(taskTO));
    }

    @Test
    public void testIncubationTaskTOWithInvalidDinosaurTypeIdIsInvalid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.getAdditionalParams().put("dinosaurTypeId", 0L);

        Assertions.assertThrows(TaskValidationException.class, () -> validator.validate(taskTO));
    }

    @Test
    public void testIncubationTaskTOWithValidDinosaurTypeIdIsValid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.getAdditionalParams().put("dinosaurTypeId", EXISTING_DINOSAUR_TYPE_ID);

        Assertions.assertDoesNotThrow(() -> validator.validate(taskTO));
    }

    @Test
    public void testTaskWithInvalidTaskTypeIsInvalid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setId(EXISTING_TASK_ID);
        taskTO.setTaskType(TaskType.RESEARCH);

        Assertions.assertThrows(TaskValidationException.class, () -> validator.validate(taskTO));
    }
}
