package com.jurassic.jurassiccrm.task.builder;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.task.builder.exception.TaskBuildException;
import com.jurassic.jurassiccrm.task.builder.exception.TaskTOBuildException;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.model.aviary.CreateAviaryTask;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TaskBuilder.class)
@MockBean(classes = {
        TaskPriorityRepository.class
})
@ActiveProfiles("test")
public class TaskBuilderTest {

    private static final Long EXISTING_USER_ID = 1L;

    private static final Long AVIARY_TYPE_ID = 1L;

    private static final Long DINOSAUR_TYPE_ID = 1L;

    @Autowired
    private TaskBuilder taskBuilder;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AviaryTypeRepository aviaryTypeRepository;

    @MockBean
    private DinosaurTypeRepository dinosaurTypeRepository;

    @Mock
    private User mockUser;

    @Mock
    private AviaryType mockAviaryType;

    @Mock
    private DinosaurType mockDinosaurType;



    @BeforeEach
    private void setup() {
        when(mockUser.getId()).thenReturn(EXISTING_USER_ID);

        when(mockAviaryType.getId()).thenReturn(AVIARY_TYPE_ID);

        when(mockDinosaurType.getId()).thenReturn(DINOSAUR_TYPE_ID);

        when(userRepository.findById(eq(EXISTING_USER_ID))).thenReturn(Optional.of(mockUser));
        when(userRepository.findById(AdditionalMatchers.not(eq(EXISTING_USER_ID)))).thenReturn(Optional.empty());

        when(aviaryTypeRepository.findById(eq(AVIARY_TYPE_ID))).thenReturn(Optional.of(mockAviaryType));
        when(aviaryTypeRepository.findById(AdditionalMatchers.not(eq(AVIARY_TYPE_ID)))).thenReturn(Optional.empty());

        when(dinosaurTypeRepository.findById(eq(DINOSAUR_TYPE_ID))).thenReturn(Optional.of(mockDinosaurType));
        when(dinosaurTypeRepository.findById(AdditionalMatchers.not(eq(DINOSAUR_TYPE_ID)))).thenReturn(Optional.empty());
    }
    
    @Test
    public void testTaskTOWithResearchTypeUsed_thenResearchTaskIsBuilt() throws TaskBuildException {

        TaskTO taskTO = TaskTO.builder().taskType(TaskType.RESEARCH).build();

        Task task = taskBuilder.buildEntityFromTO(taskTO);

        Assertions.assertTrue(task instanceof ResearchTask,
                "ResearchTask must be created from TaskTO with Research task type");

    }

    @Test
    public void testTaskTOWithInvalidCreatedByIdFieldUsed_thenExceptionThrown() {

        TaskTO taskTO = TaskTO.builder().taskType(TaskType.RESEARCH).build();

        taskTO.setCreatedById(2L);

        Assertions.assertThrows(TaskBuildException.class, () -> taskBuilder.buildEntityFromTO(taskTO),
                "Exception should be thrown when taskTO with invalid user id is used");
    }

    @Test
    public void testTaskWithMissingTaskTypeUsed_thenExceptionThrown() {

        TaskTO taskTO = new TaskTO();

        Assertions.assertThrows(TaskBuildException.class, () -> taskBuilder.buildEntityFromTO(taskTO),
                "Exception should be thrown when taskTO without task type is used");

    }

    @Test
    public void testCreatedByFieldPresent_thenTaskTOHasCreatedById() {

        Task task = new ResearchTask();

        task.setTaskType(TaskType.RESEARCH);

        task.setCreatedBy(mockUser);

        TaskTO taskTO = taskBuilder.buildTOFromEntity(task);

        Assertions.assertEquals(1L, taskTO.getCreatedById());
    }
    
    @Test
    public void testTaskWithoutTaskTypeUsed_thenExceptionThrown() {

        Task task = new ResearchTask();
        task.setTaskType(null);

        Assertions.assertThrows(TaskTOBuildException.class, () -> taskBuilder.buildTOFromEntity(task));

    }

    @Test
    public void testCreateAviaryTaskUsed_thenTaskTOHasAviaryTypeIdInAdditionalParams() {

        CreateAviaryTask task = new CreateAviaryTask();

        task.setAviaryType(mockAviaryType);

        TaskTO taskTO = taskBuilder.buildTOFromEntity(task);

        Long aviaryTypeId = (Long) taskTO.getAdditionalParams().getOrDefault("aviaryTypeId", 0);

        Assertions.assertEquals(AVIARY_TYPE_ID, aviaryTypeId);
    }

    @Test
    public void testTaskTOHasCreateAviaryTypeAndAdditionalParamsContainValidAviaryTypeId_thenTaskCreated()
            throws TaskBuildException {

        Map<String, Object> additionalParams = new HashMap<>();
        additionalParams.put("aviaryTypeId", AVIARY_TYPE_ID);

        TaskTO taskTO = TaskTO.builder().taskType(TaskType.AVIARY_CREATION).additionalParams(additionalParams).build();

        CreateAviaryTask task = (CreateAviaryTask) taskBuilder.buildEntityFromTO(taskTO);

        Assertions.assertEquals(mockAviaryType, task.getAviaryType());
    }

}
