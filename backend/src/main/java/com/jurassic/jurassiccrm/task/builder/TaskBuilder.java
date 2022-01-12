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
import com.jurassic.jurassiccrm.task.model.aviary.CreateAviaryTask;
import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TaskBuilder {

    private static final String AVIARY_TYPE_ID_FIELD_NAME = "aviaryTypeId";
    private static final String DINOSAUR_TYPE_ID_FIELD_NAME = "dinosaurTypeId";

    private final UserRepository userRepository;
    private final TaskPriorityRepository taskPriorityRepository;
    private final AviaryTypeRepository aviaryTypeRepository;
    private final DinosaurTypeRepository dinosaurTypeRepository;

    @Autowired
    public TaskBuilder(UserRepository userRepository,
                       TaskPriorityRepository taskPriorityRepository,
                       AviaryTypeRepository aviaryTypeRepository,
                       DinosaurTypeRepository dinosaurTypeRepository) {
        this.userRepository = userRepository;
        this.taskPriorityRepository = taskPriorityRepository;
        this.aviaryTypeRepository = aviaryTypeRepository;
        this.dinosaurTypeRepository = dinosaurTypeRepository;
    }

    public Task buildEntityFromTO(TaskTO taskTO) throws TaskBuildException {
        if (Objects.isNull(taskTO.getTaskType())) {
            throw new TaskBuildException();
        }
        switch (taskTO.getTaskType()) {
            case AVIARY_CREATION: return getCreateAviaryTaskFromTO(taskTO);
            case INCUBATION: return getIncubationTaskFromTO(taskTO);
            case RESEARCH: return getResearchTaskFromTO(taskTO);
            default: {
                throw new TaskBuildException();
            }
        }
    }

    private CreateAviaryTask getCreateAviaryTaskFromTO(TaskTO taskTO) throws TaskBuildException {
        CreateAviaryTask task = new CreateAviaryTask();
        setBaseFields(taskTO, task);

        Long aviaryTypeId = (Long) taskTO.getAdditionalParams().getOrDefault(AVIARY_TYPE_ID_FIELD_NAME, null);

        if (Objects.isNull(aviaryTypeId)) {
            task.setAviaryType(null);
        } else {
            AviaryType aviaryType = aviaryTypeRepository.findById(aviaryTypeId).orElseThrow(TaskBuildException::new);
            task.setAviaryType(aviaryType);
        }

        return task;
    }

    private IncubationTask getIncubationTaskFromTO(TaskTO taskTO) throws TaskBuildException {
        IncubationTask task = new IncubationTask();
        setBaseFields(taskTO, task);

        Long dinosaurTypeId = (Long) taskTO.getAdditionalParams().getOrDefault(DINOSAUR_TYPE_ID_FIELD_NAME, null);

        if (Objects.isNull(dinosaurTypeId)) {
            task.setDinosaurType(null);
        } else {
            DinosaurType dinosaurType = dinosaurTypeRepository.findById(dinosaurTypeId).orElseThrow(TaskBuildException::new);
            task.setDinosaurType(dinosaurType);
        }

        return task;
    }

    private ResearchTask getResearchTaskFromTO(TaskTO taskTO) throws TaskBuildException {
        ResearchTask task = new ResearchTask();
        setBaseFields(taskTO, task);

        return task;
    }

    private void setBaseFields(TaskTO source, Task target) throws TaskBuildException {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setTaskType(source.getTaskType());

        target.setCreatedBy(getUserByIdOrThrowException(source.getCreatedById()));
        target.setAssignee(getUserByIdOrThrowException(source.getAssigneeId()));
        target.setLastUpdater(getUserByIdOrThrowException(source.getLastUpdaterId()));

        target.setPriority(getTaskPriorityByIdOrThrowException(source.getTaskPriorityId()));

        target.setCreated(convertLocalDateToTimestamp(source.getCreated()));
        target.setLastUpdated(convertLocalDateToTimestamp(source.getLastUpdated()));
    }

    public <T extends Task> TaskTO buildTOFromEntity(T task) {
        Map<String, Object> additionalFields = getTaskSpecificFields(task);

        return TaskTO.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .assigneeId(safeGetUserIdFieldOrNull(task.getAssignee()))
                .createdById(safeGetUserIdFieldOrNull(task.getCreatedBy()))
                .lastUpdaterId(safeGetUserIdFieldOrNull(task.getLastUpdater()))
                .created(safeGetLocalDateFieldOrNull(task.getCreated()))
                .lastUpdated(safeGetLocalDateFieldOrNull(task.getLastUpdated()))
                .taskPriorityId(safeGetPriorityIdOrNull(task.getPriority()))
                .additionalParams(additionalFields).build();
    }

    private Map<String, Object> getTaskSpecificFields(Task task) {
        if (Objects.isNull(task.getTaskType())) {
            throw new TaskTOBuildException();
        }
        switch (task.getTaskType()) {
            case RESEARCH:
                return getResearchSpecificFields((ResearchTask)task);
            case INCUBATION:
                return getIncubationSpecificFields((IncubationTask)task);
            case AVIARY_CREATION:
                return getCreateAviarySpecificFields((CreateAviaryTask)task);
            default:
                return new HashMap<>();
        }
    }

    private Map<String, Object> getIncubationSpecificFields(IncubationTask incubationTask) {
        Map<String, Object> additionalFields = new HashMap<>();

        DinosaurType dinosaurType = incubationTask.getDinosaurType();

        additionalFields.put(DINOSAUR_TYPE_ID_FIELD_NAME, getIdFromNullable(dinosaurType, () -> dinosaurType.getId()));
        return additionalFields;
    }

    private Map<String, Object> getResearchSpecificFields(ResearchTask researchTask) {
        Map<String, Object> additionalFields = new HashMap<>();
        return additionalFields;
    }

    private Map<String, Object> getCreateAviarySpecificFields(CreateAviaryTask createAviaryTask) {
        Map<String, Object> additionalFields = new HashMap<>();

        AviaryType aviaryType = createAviaryTask.getAviaryType();

        additionalFields.put(AVIARY_TYPE_ID_FIELD_NAME, getIdFromNullable(aviaryType, aviaryType::getId));
        return additionalFields;
    }

    private Long safeGetUserIdFieldOrNull(User user) {
        return Optional.ofNullable(user).map(User::getId).orElse(null);
    }

    private LocalDate safeGetLocalDateFieldOrNull(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(Timestamp::toLocalDateTime)
                .map(LocalDateTime::toLocalDate)
                .orElse(null);
    }

    private Long safeGetPriorityIdOrNull(TaskPriority taskPriority) {
        return Optional.ofNullable(taskPriority).map(TaskPriority::getId).orElse(null);
    }

    private User getUserByIdOrThrowException(Long id) throws TaskBuildException {
        if (Objects.isNull(id)) {
            return null;
        } else {
            return userRepository.findById(id).orElseThrow(TaskBuildException::new);
        }
    }

    private TaskPriority getTaskPriorityByIdOrThrowException(Long id) throws TaskBuildException {
        if (Objects.isNull(id)) {
            return null;
        } else {
            return taskPriorityRepository.findById(id).orElseThrow(TaskBuildException::new);
        }
    }

    private Timestamp convertLocalDateToTimestamp(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        } else {
            return Timestamp.valueOf(localDate.atStartOfDay());
        }
    }

    private Long getIdFromNullable(@Nullable Object o, Supplier<Long> idSupplier) {
        if (Objects.isNull(o)) {
            return null;
        } else {
            return idSupplier.get();
        }
    }

}
