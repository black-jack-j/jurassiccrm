package com.jurassic.jurassiccrm.task.dto;

import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.task.dto.validation.TaskTOMessages;
import com.jurassic.jurassiccrm.validation.existence.NullOrExists;
import com.jurassic.jurassiccrm.validation.groups.OnCreate;
import com.jurassic.jurassiccrm.validation.groups.OnUpdate;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
@ApiModel(parent = TaskTO.class)
public class AviaryTaskDTO extends TaskTO {

    public static final String SQUARE_CONSTRAINT_VIOLATION = "square must be greater than 0";

    @NullOrExists(
            message = TaskTOMessages.ENTITY_EXISTENCE_CONSTRAINT_VIOLATION,
            repository = AviaryTypeRepository.class,
            groups = {OnCreate.class, OnUpdate.class}
    )
    private Long aviaryTypeId;

    @Positive(message = SQUARE_CONSTRAINT_VIOLATION, groups = {OnCreate.class, OnUpdate.class})
    private Long square;

}
