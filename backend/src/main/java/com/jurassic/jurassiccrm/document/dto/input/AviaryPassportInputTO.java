package com.jurassic.jurassiccrm.document.dto.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class AviaryPassportInputTO extends DocumentInputTO {
    @NotBlank
    private Long aviaryTypeId;

    @NotBlank
    private Long code;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate builtDate;

    @NotBlank
    private Integer revisionPeriod;

    @NotBlank
    @Size(min = 3, max = 255)
    private String status;

    public AviaryPassport toAviaryPassport(){
        val document = new AviaryPassport();
        setBaseFields(document);
        document.setAviaryType(new AviaryType(aviaryTypeId));
        document.setCode(code);
        document.setBuiltDate(builtDate);
        document.setRevisionPeriod(revisionPeriod);
        document.setStatus(status);
        return document;
    }
}
