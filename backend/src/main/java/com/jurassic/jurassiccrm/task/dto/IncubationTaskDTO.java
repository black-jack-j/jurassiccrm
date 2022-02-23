package com.jurassic.jurassiccrm.task.dto;


import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import com.jurassic.jurassiccrm.validation.existence.NullOrExists;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(parent = TaskTO.class)
public class IncubationTaskDTO extends TaskTO {

    @NullOrExists(repository = DinosaurTypeRepository.class)
    private Long dinosaurTypeId;

    public static IncubationTaskDTO fromTask(IncubationTask task) {
        IncubationTaskDTO dto = new IncubationTaskDTO();
        dto.setBaseFields(task);
        dto.setDinosaurTypeId(task.getDinosaurType().getId());
        return dto;
    }
}
