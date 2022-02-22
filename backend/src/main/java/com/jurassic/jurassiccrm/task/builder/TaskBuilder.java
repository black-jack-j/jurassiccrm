package com.jurassic.jurassiccrm.task.builder;

import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.task.dto.AviaryTaskDTO;
import com.jurassic.jurassiccrm.task.dto.IncubationTaskDTO;
import com.jurassic.jurassiccrm.task.dto.ResearchTaskDTO;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.aviary.CreateAviaryTask;
import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TaskBuilder {

    private final UserRepository userRepository;
    private final TaskPriorityRepository taskPriorityRepository;
    private final AviaryTypeRepository aviaryTypeRepository;
    private final DinosaurTypeRepository dinosaurTypeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskBuilder(UserRepository userRepository,
                       TaskPriorityRepository taskPriorityRepository,
                       AviaryTypeRepository aviaryTypeRepository,
                       DinosaurTypeRepository dinosaurTypeRepository,
                       ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.taskPriorityRepository = taskPriorityRepository;
        this.aviaryTypeRepository = aviaryTypeRepository;
        this.dinosaurTypeRepository = dinosaurTypeRepository;
        this.modelMapper = modelMapper;
    }

    public Task buildEntityFromTO(TaskTO taskTO) {
        if (taskTO instanceof AviaryTaskDTO) {
            return buildAviaryTaskFromTO((AviaryTaskDTO) taskTO);
        } else if (taskTO instanceof ResearchTaskDTO) {
            return buildResearchTaskFromTO((ResearchTaskDTO) taskTO);
        } else if (taskTO instanceof IncubationTaskDTO) {
            return buildIncubationTaskFromTO((IncubationTaskDTO) taskTO);
        } else {
            throw new IllegalArgumentException("Unkown type of TaskTO: " + taskTO.getClass());
        }
    }

    public CreateAviaryTask buildAviaryTaskFromTO(AviaryTaskDTO taskDTO) {
        CreateAviaryTask createAviaryTask = new CreateAviaryTask();
        setBaseFields(createAviaryTask, taskDTO);

        createAviaryTask.setAviaryType(Optional.ofNullable(taskDTO.getAviaryType())
                .map(SimpleEntityOutputTO::getId)
                .map(aviaryTypeRepository::getOne).orElse(null));
        createAviaryTask.setAviarySquare(taskDTO.getSquare());
        return createAviaryTask;
    }

    public ResearchTask buildResearchTaskFromTO(ResearchTaskDTO researchTaskDTO) {
        ResearchTask researchTask = new ResearchTask();
        setBaseFields(researchTask, researchTaskDTO);

        researchTask.setPurpose(researchTaskDTO.getPurpose());
        return researchTask;
    }

    public IncubationTask buildIncubationTaskFromTO(IncubationTaskDTO incubationTaskDTO) {
        IncubationTask incubationTask = new IncubationTask();
        setBaseFields(incubationTask, incubationTaskDTO);

        incubationTask.setDinosaurType(Optional.ofNullable(incubationTaskDTO.getDinosaurType())
                .map(SimpleEntityOutputTO::getId)
                .map(dinosaurTypeRepository::getOne).orElse(null));
        return incubationTask;
    }

    private void setBaseFields(Task target, TaskTO source) {
        target.setId(source.getId());
        target.setPriority(Optional.ofNullable(source.getPriority())
                .map(SimpleEntityOutputTO::getId)
                .map(taskPriorityRepository::getOne).orElse(null));
        target.setName(source.getName());
        target.setAssignee(Optional.ofNullable(source.getAssigneeId())
                .map(userRepository::getOne).orElse(null));
        target.setDescription(source.getDescription());
    }

    public <T extends Task> TaskTO buildTOFromEntity(T task) {
        if (task instanceof CreateAviaryTask) {
            return mapEntityToTO(task, AviaryTaskDTO.class);
        } else if (task instanceof ResearchTask) {
            return mapEntityToTO(task, ResearchTaskDTO.class);
        } else if (task instanceof IncubationTask) {
            return mapEntityToTO(task, IncubationTaskDTO.class);
        } else {
            throw new IllegalArgumentException("Unkown type of TaskTO: " + task.getClass());
        }
    }

    private TaskTO mapEntityToTO(Task entity, Class<? extends TaskTO> destinationClass) {
        return modelMapper.map(entity, destinationClass);
    }
}
