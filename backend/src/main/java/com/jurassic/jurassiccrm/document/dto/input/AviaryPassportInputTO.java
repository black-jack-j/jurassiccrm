package com.jurassic.jurassiccrm.document.dto.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AviaryPassportInputTO extends DocumentInputTO {
    @NotNull
    private Long aviaryTypeId;

    @NotNull
    private Long code;

    @NotNull
    private Long square;

    @NotNull
    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date builtDate;

    @NotNull
    @Max(366)
    @Positive
    private Integer revisionPeriod;

    @NotBlank
    @Size(min = 3, max = 255)
    private String status;

    public AviaryPassport toAviaryPassport(){
        val document = new AviaryPassport();
        setBaseFields(document);
        document.setAviaryType(new AviaryType(aviaryTypeId));
        document.setCode(code);
        document.setBuiltDate(LocalDateTime.ofInstant(builtDate.toInstant(), ZoneId.of("Z")).toLocalDate());
        document.setRevisionPeriod(revisionPeriod);
        document.setStatus(status);
        document.setSquare(square);
        return document;
    }
}
