package com.jurassic.jurassiccrm.task.dto;


import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.validation.existence.NullOrExists;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(parent = TaskTO.class)
public class IncubationTaskDTO extends TaskTO {

    @NullOrExists(repository = DinosaurTypeRepository.class)
    private Long dinosaurTypeId;

}
