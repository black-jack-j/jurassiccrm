package com.jurassic.jurassiccrm.aviary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@ApiModel
@AllArgsConstructor
public class AviaryRevisionEntryTO {

    private SimpleEntityOutputTO aviary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate revisionDate;

    public static AviaryRevisionEntryTO fromEntity(AviaryPassport entity) {
        return AviaryRevisionEntryTO.builder()
                .aviary(new SimpleEntityOutputTO(entity.getId(), entity.getName()))
                .revisionDate(entity.getNextRevisionDate())
                .build();
    }

}
