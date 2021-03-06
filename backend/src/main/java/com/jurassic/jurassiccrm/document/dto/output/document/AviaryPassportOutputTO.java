package com.jurassic.jurassiccrm.document.dto.output.document;

import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class AviaryPassportOutputTO extends DocumentOutputTO {
    private SimpleEntityOutputTO aviaryType;
    private String code;
    private Instant builtDate;
    private Integer revisionPeriod;
    private String status;
    private Long square;

    public static AviaryPassportOutputTO fromDocument(AviaryPassport aviaryPassport){
        AviaryPassportOutputTO dto = new AviaryPassportOutputTO();
        dto.setBaseFields(aviaryPassport);
        dto.setAviaryType(SimpleEntityOutputTO.fromEntity(aviaryPassport.getAviaryType()));
        dto.setCode(aviaryPassport.getCode());
        dto.setBuiltDate(aviaryPassport.getBuiltDate());
        dto.setRevisionPeriod(aviaryPassport.getRevisionPeriod());
        dto.setStatus(aviaryPassport.getStatus());
        dto.setSquare(aviaryPassport.getSquare());
        return dto;
    }
}
