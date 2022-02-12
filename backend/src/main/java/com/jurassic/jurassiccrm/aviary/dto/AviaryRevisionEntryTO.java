package com.jurassic.jurassiccrm.aviary.dto;

import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@ApiModel
@AllArgsConstructor
public class AviaryRevisionEntryTO {

    private SimpleEntityOutputTO aviary;

    private Instant revisionDate;

    public static AviaryRevisionEntryTO fromEntity(AviaryPassport entity) {
        return AviaryRevisionEntryTO.builder()
                .aviary(new SimpleEntityOutputTO(entity.getId(), entity.getName()))
                .revisionDate(entity.getNextRevisionDate())
                .build();
    }

}
