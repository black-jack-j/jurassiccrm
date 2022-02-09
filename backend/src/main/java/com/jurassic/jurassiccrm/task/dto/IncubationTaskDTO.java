package com.jurassic.jurassiccrm.task.dto;


import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.validation.existence.NullOrExists;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class IncubationTaskDTO extends TaskTO {

    @NullOrExists(repository = DinosaurTypeRepository.class)
    private Long dinosaurTypeId;

}
