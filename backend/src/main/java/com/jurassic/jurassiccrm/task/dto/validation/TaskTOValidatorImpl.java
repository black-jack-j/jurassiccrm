package com.jurassic.jurassiccrm.task.dto.validation;

import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.task.dao.TaskRepository;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.dto.validation.exception.TaskValidationException;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TaskTOValidatorImpl implements TaskTOValidator {

    private final UserRepository userRepository;
    private final TaskPriorityRepository taskPriorityRepository;
    private final TaskRepository taskRepository;
    private final AviaryTypeRepository aviaryTypeRepository;
    private final DinosaurTypeRepository dinosaurTypeRepository;

    public TaskTOValidatorImpl(UserRepository userRepository,
                               TaskPriorityRepository taskPriorityRepository,
                               TaskRepository taskRepository,
                               AviaryTypeRepository aviaryTypeRepository,
                               DinosaurTypeRepository dinosaurTypeRepository) {
        this.userRepository = userRepository;
        this.taskPriorityRepository = taskPriorityRepository;
        this.taskRepository = taskRepository;
        this.aviaryTypeRepository = aviaryTypeRepository;
        this.dinosaurTypeRepository = dinosaurTypeRepository;
    }

    public void validate(TaskTO taskTO) throws TaskValidationException {
        if (Objects.isNull(taskTO.getName())) throw new TaskValidationException();

        if (Objects.isNull(taskTO.getTaskType())) throw new TaskValidationException();

        validateTaskExistsForNullableIdAndHasSameTypeOrThrowException(taskTO.getId(), taskTO);

        validateObjectExistForNullableIdOrThrowException(taskTO.getTaskPriorityId(), taskPriorityRepository::findById);

        validateObjectExistForNullableIdOrThrowException(taskTO.getLastUpdaterId(), userRepository::findById);
        validateObjectExistForNullableIdOrThrowException(taskTO.getCreatedById(), userRepository::findById);
        validateObjectExistForNullableIdOrThrowException(taskTO.getAssigneeId(), userRepository::findById);

        switch (taskTO.getTaskType()) {
            case RESEARCH: {
                validateResearchTask(taskTO);
                break;
            }
            case INCUBATION: {
                validateIncubationTask(taskTO);
                break;
            }
            case AVIARY_CREATION: {
                validateCreateAviaryTask(taskTO);
                break;
            }
            default: {
                throw new TaskValidationException();
            }
        }
    }

    private void validateCreateAviaryTask(TaskTO taskTO) throws TaskValidationException {
        Map<String, Object> additionalParams = taskTO.getAdditionalParams();

        Long aviaryTypeId = (Long) additionalParams.getOrDefault("aviaryTypeId", null);

        validateObjectExistForNullableIdOrThrowException(aviaryTypeId, aviaryTypeRepository::findById);
    }

    private void validateIncubationTask(TaskTO taskTO) throws TaskValidationException {
        Map<String, Object> additionalParams = taskTO.getAdditionalParams();

        Long dinoTypeId = (Long) additionalParams.getOrDefault("dinosaurTypeId", null);

        validateObjectExistForNullableIdOrThrowException(dinoTypeId, dinosaurTypeRepository::findById);
    }

    private void validateResearchTask(TaskTO taskTO) throws TaskValidationException {

    }

    private void validateTaskExistsForNullableIdAndHasSameTypeOrThrowException(@Nullable Long taskId, TaskTO taskTO)
            throws TaskValidationException {
        if (!Objects.isNull(taskId)) {
            Task task = taskRepository.findById(taskId).orElseThrow(TaskValidationException::new);
            if (taskTO.getTaskType() != task.getTaskType()) {
                throw new TaskValidationException();
            }

        }
    }

    private <T> void validateObjectExistForNullableIdOrThrowException(@Nullable Long id, Function<Long, Optional<T>> supplier)
            throws TaskValidationException {
        if (!Objects.isNull(id)) {
            supplier.apply(id).orElseThrow(TaskValidationException::new);
        }
    }

}
