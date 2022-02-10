package com.jurassic.jurassiccrm.task.dto.validation;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;
import com.jurassic.jurassiccrm.validation.groups.OnCreate;
import com.jurassic.jurassiccrm.validation.groups.OnUpdate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class TaskTOValidatorTest {

    private static final Long EXISTING_USER_ID = 1L;
    private static final Long EXISTING_TASK_ID = 1L;
    private static final Long EXISTING_AVIARY_TYPE_ID = 1L;
    private static final Long EXISTING_DINOSAUR_TYPE_ID = 1L;
    private static final Long EXISTING_TASK_PRIORITY_ID = 1L;

    private LocalValidatorFactoryBean validator;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

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
        SpringConstraintValidatorFactory constraintValidatorFactory = new SpringConstraintValidatorFactory(
                applicationContext.getAutowireCapableBeanFactory()
        );
        validator = new LocalValidatorFactoryBean();
        validator.setConstraintValidatorFactory(constraintValidatorFactory);
        validator.setApplicationContext(applicationContext);
        validator.afterPropertiesSet();

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
        when(repository.existsById(eq(id))).thenReturn(true);
        when(repository.existsById(AdditionalMatchers.not(eq(id)))).thenReturn(false);
    }

    private TaskTO getValidTaskTOOfType(TaskType taskType) {
        when(task.getTaskType()).thenReturn(taskType);

        /*return TaskTO.builder()
                .id(EXISTING_TASK_ID)
                .additionalParams(new HashMap<>())
                .taskType(taskType)
                .name("test")
                .description("test").build();*/
        return null;
    }

    @Test
    public void testValidTaskTO_thenNoViolations() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);
        Set<ConstraintViolation<TaskTO>> constraintViolationSet = validator.validate(taskTO, OnCreate.class);
        Assertions.assertTrue(constraintViolationSet.isEmpty());
    }

    @Test
    public void testTaskTOWithoutIdForUpdate_thenViolation() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);
        taskTO.setId(null);
        Set<ConstraintViolation<TaskTO>> constraintViolations = validator.validate(taskTO, OnUpdate.class);
        Assertions.assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testTaskWithoutNameIsInvalid() {
        TaskTO task = getValidTaskTOOfType(TaskType.INCUBATION);

        task.setName(null);
        Set<ConstraintViolation<TaskTO>> constraintViolationSet = validator.validate(task, OnCreate.class);

        Assertions.assertFalse(constraintViolationSet.isEmpty());

        boolean nameViolation = constraintViolationSet.stream()
                .map(ConstraintViolation::getMessage)
                .anyMatch(TaskTOMessages.NAME_CONSTRAINT_VIOLATION::equals);

        Assertions.assertTrue(nameViolation);
    }

    @Test
    public void testTaskWithoutTypeIsInvalid() {
        TaskTO task = getValidTaskTOOfType(TaskType.INCUBATION);

        task.setTaskType(null);
        Set<ConstraintViolation<TaskTO>> constraintViolationSet = validator.validate(task, OnCreate.class);

        Assertions.assertFalse(constraintViolationSet.isEmpty());

        boolean typeViolation = constraintViolationSet.stream()
                .map(ConstraintViolation::getMessage)
                .anyMatch(TaskTOMessages.TASK_TYPE_CONSTRAINT_VIOLATION::equals);

        Assertions.assertTrue(typeViolation);
    }

    @Test
    public void testTaskWithInvalidCreatedByIsInvalid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setCreatedById(0L);

        Set<ConstraintViolation<TaskTO>> constraintViolationSet = validator.validate(taskTO, OnCreate.class);

        Assertions.assertFalse(constraintViolationSet.isEmpty());

        boolean createdByIdViolation = constraintViolationSet.stream()
                .map(ConstraintViolation::getMessage)
                .anyMatch(TaskTOMessages.ENTITY_EXISTENCE_CONSTRAINT_VIOLATION::equals);

        Assertions.assertTrue(createdByIdViolation);
    }

    @Test
    public void testTaskWithExistingCreatedByIdIsValid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setCreatedById(EXISTING_USER_ID);

        Set<ConstraintViolation<TaskTO>> constraintViolationSet = validator.validate(taskTO, OnCreate.class);
        Assertions.assertTrue(constraintViolationSet.isEmpty());

    }

    @Test
    public void testTaskTOWithValidTaskIdIsValid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setId(EXISTING_TASK_ID);

        Set<ConstraintViolation<TaskTO>> constraintViolationSet = validator.validate(taskTO, OnUpdate.class);

        Assertions.assertTrue(constraintViolationSet.isEmpty());
    }

    @Test
    public void testTaskTOWithInvalidTaskPriorityIdIsInvalid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setTaskPriorityId(0L);

        Set<ConstraintViolation<TaskTO>> constraintViolationSet = validator.validate(taskTO, OnCreate.class);

        Assertions.assertFalse(constraintViolationSet.isEmpty());

        boolean priorityIdViolation = constraintViolationSet.stream()
                .map(ConstraintViolation::getMessage)
                .anyMatch(TaskTOMessages.ENTITY_EXISTENCE_CONSTRAINT_VIOLATION::equals);

        Assertions.assertTrue(priorityIdViolation);
    }

    @Test
    public void testTaskTOWithValidTaskPriorityIdIsValid() {
        TaskTO taskTO = getValidTaskTOOfType(TaskType.INCUBATION);

        taskTO.setTaskPriorityId(EXISTING_TASK_PRIORITY_ID);

        Set<ConstraintViolation<TaskTO>> constraintViolationSet = validator.validate(taskTO, OnCreate.class);

        Assertions.assertTrue(constraintViolationSet.isEmpty());
    }
}
