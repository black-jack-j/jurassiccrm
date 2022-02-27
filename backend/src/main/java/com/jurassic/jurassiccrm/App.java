package com.jurassic.jurassiccrm;

import com.jurassic.jurassiccrm.task.dto.AviaryTaskDTO;
import com.jurassic.jurassiccrm.task.dto.IncubationTaskDTO;
import com.jurassic.jurassiccrm.task.dto.ResearchTaskDTO;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.aviary.CreateAviaryTask;
import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Optional;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Task, TaskTO> baseTypeMap = modelMapper.createTypeMap(Task.class, TaskTO.class);

        baseTypeMap.include(CreateAviaryTask.class, AviaryTaskDTO.class);
        baseTypeMap.include(IncubationTask.class, IncubationTaskDTO.class);
        baseTypeMap.include(ResearchTask.class, ResearchTaskDTO.class);

        setTimeConverters(modelMapper);
        setNextStatesConverters(modelMapper);

        return modelMapper;
    }

    private void setTimeConverters(ModelMapper modelMapper) {
        modelMapper.typeMap(CreateAviaryTask.class, AviaryTaskDTO.class)
                .addMappings(mapping -> mapping.using(timestampLocalDateConverter).map(CreateAviaryTask::getCreated, AviaryTaskDTO::setCreated));
        modelMapper.typeMap(CreateAviaryTask.class, AviaryTaskDTO.class)
                .addMappings(mapping -> mapping.using(timestampLocalDateConverter).map(CreateAviaryTask::getLastUpdated, AviaryTaskDTO::setLastUpdated));

        modelMapper.typeMap(ResearchTask.class, ResearchTaskDTO.class)
                .addMappings(mapping -> mapping.using(timestampLocalDateConverter).map(ResearchTask::getCreated, ResearchTaskDTO::setCreated));
        modelMapper.typeMap(ResearchTask.class, ResearchTaskDTO.class)
                .addMappings(mapping -> mapping.using(timestampLocalDateConverter).map(ResearchTask::getLastUpdated, ResearchTaskDTO::setLastUpdated));

        modelMapper.typeMap(IncubationTask.class, IncubationTaskDTO.class)
                .addMappings(mapping -> mapping.using(timestampLocalDateConverter).map(IncubationTask::getCreated, IncubationTaskDTO::setCreated));
        modelMapper.typeMap(IncubationTask.class, IncubationTaskDTO.class)
                .addMappings(mapping -> mapping.using(timestampLocalDateConverter).map(IncubationTask::getLastUpdated, IncubationTaskDTO::setLastUpdated));
    }

    private void setNextStatesConverters(ModelMapper modelMapper) {
        modelMapper.typeMap(CreateAviaryTask.class, AviaryTaskDTO.class)
                .addMappings(mapping -> mapping.map(CreateAviaryTask::getPossibleNextStates, AviaryTaskDTO::setPossibleNextStates));
        modelMapper.typeMap(ResearchTask.class, ResearchTaskDTO.class)
                .addMappings(mapping -> mapping.map(ResearchTask::getPossibleNextStates, ResearchTaskDTO::setPossibleNextStates));
        modelMapper.typeMap(IncubationTask.class, IncubationTaskDTO.class)
                .addMappings(mapping -> mapping.map(IncubationTask::getPossibleNextStates, IncubationTaskDTO::setPossibleNextStates));
    }

    private static final Converter<Instant, Instant> timestampLocalDateConverter = ctx -> Optional.ofNullable(ctx.getSource())
            .orElse(null);
}
