package com.jurassic.jurassiccrm.aviary.dto;

import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.controller.to.RevisableEntity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@ApiModel
@AllArgsConstructor
public class RevisionEntryTO {

    private SimpleEntityOutputTO entity;

    private Instant revisionDate;

    public static RevisionEntryTO fromEntity(RevisableEntity entity) {
        return RevisionEntryTO.builder()
                .entity(new SimpleEntityOutputTO(entity.getId(), entity.getName()))
                .revisionDate(entity.getNextRevisionDate())
                .build();
    }

}
