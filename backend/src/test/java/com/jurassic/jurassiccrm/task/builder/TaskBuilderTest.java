package com.jurassic.jurassiccrm.task.builder;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.task.dto.AviaryTaskDTO;
import com.jurassic.jurassiccrm.task.dto.IncubationTaskDTO;
import com.jurassic.jurassiccrm.task.dto.ResearchTaskDTO;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.aviary.CreateAviaryTask;
import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TaskBuilder.class)
@MockBean(classes = {
        TaskPriorityRepository.class
})
@ActiveProfiles("test")
@Import(ModelMapper.class)
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

    private ModelMapper modelMapper;



    @BeforeEach
    private void setup() {

        modelMapper = new ModelMapper();
        TypeMap<Task, TaskTO> baseTypeMap = modelMapper.createTypeMap(Task.class, TaskTO.class);

        baseTypeMap.include(CreateAviaryTask.class, AviaryTaskDTO.class);
        baseTypeMap.include(IncubationTask.class, IncubationTaskDTO.class);
        baseTypeMap.include(ResearchTask.class, ResearchTaskDTO.class);

        Converter<Instant, Instant> timestampLocalDateConverter = ctx -> Optional.of(ctx.getSource())
               .orElse(null);

        modelMapper.addConverter(timestampLocalDateConverter);

        baseTypeMap.addMappings(mapper -> mapper.using(timestampLocalDateConverter).map(Task::getCreated, TaskTO::setCreated));
        baseTypeMap.addMappings(mapping -> mapping.using(timestampLocalDateConverter).map(Task::getLastUpdated, TaskTO::setLastUpdated));
        modelMapper.typeMap(ResearchTask.class, ResearchTaskDTO.class).addMappings(mapping -> mapping.using(timestampLocalDateConverter).map(ResearchTask::getCreated, ResearchTaskDTO::setCreated));

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
    public void testModelMapper() {
        Task source = new ResearchTask();
        source.setId(10L);
        source.setCreated(Instant.now());
        TaskPriority priority = new TaskPriority();
        priority.setId(12L);
        source.setPriority(priority);
        ResearchTaskDTO target = modelMapper.map(source, ResearchTaskDTO.class);
        System.out.println(target);
    }

    /*@Test
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

    @Test
    public void testTaskBuilt_thenTOHasSameCurrentStateAsEntity() {
        Task task = EntitiesUtil.getTask("test", TaskType.AVIARY_CREATION);
        task.setStatus(TaskState.OPEN);

        TaskTO taskTO = taskBuilder.buildTOFromEntity(task);
        Assertions.assertEquals(task.getStatus(), taskTO.getCurrentState());
    }*/

}
