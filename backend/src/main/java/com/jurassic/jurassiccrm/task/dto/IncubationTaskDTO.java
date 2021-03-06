package com.jurassic.jurassiccrm.task.dto;


import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import com.jurassic.jurassiccrm.validation.existence.NullOrExists;
import com.jurassic.jurassiccrm.validation.groups.OnCreate;
import com.jurassic.jurassiccrm.validation.groups.OnUpdate;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(parent = TaskTO.class)
public class IncubationTaskDTO extends TaskTO {

    @NullOrExists(
            repository = DinosaurTypeRepository.class,
            groups = {OnCreate.class, OnUpdate.class}
    )
    private SimpleEntityOutputTO dinosaurType;

    public static IncubationTaskDTO fromTask(IncubationTask task) {
        IncubationTaskDTO dto = new IncubationTaskDTO();
        dto.setBaseFields(task);
        dto.setDinosaurType(new SimpleEntityOutputTO(task.getDinosaurType().getId(), task.getDinosaurType().getName()));
        return dto;
    }
}
