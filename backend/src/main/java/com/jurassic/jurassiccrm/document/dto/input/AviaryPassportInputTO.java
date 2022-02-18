package com.jurassic.jurassiccrm.document.dto.input;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

import javax.validation.constraints.*;
import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
public class AviaryPassportInputTO extends DocumentInputTO {
    @NotNull
    private Long aviaryTypeId;

    @NotNull
    private String code;

    @NotNull
    private Long square;

    @NotNull
    @PastOrPresent
    private Instant builtDate;

    @NotNull
    @Max(366)
    @Positive
    private Integer revisionPeriod;

    @NotBlank
    @Size(min = 3, max = 255)
    private String status;

    public void setBuiltDate(Long millis) {
        this.builtDate = Instant.ofEpochMilli(millis);
    }

    public AviaryPassport toAviaryPassport(){
        val document = new AviaryPassport();
        setBaseFields(document);
        document.setAviaryType(new AviaryType(aviaryTypeId));
        document.setCode(code);
        document.setBuiltDate(builtDate);
        document.setRevisionPeriod(revisionPeriod);
        document.setStatus(status);
        document.setSquare(square);
        return document;
    }
}
