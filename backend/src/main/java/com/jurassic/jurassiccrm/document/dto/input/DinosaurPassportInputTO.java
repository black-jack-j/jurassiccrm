package com.jurassic.jurassiccrm.document.dto.input;

import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.model.DinosaurPassport;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
public class DinosaurPassportInputTO extends DocumentInputTO {
    @NotNull
    private Long dinosaurTypeId;

    @NotBlank
    @Size(min = 3, max=255)
    private String dinosaurName;

    @NotNull
    @Positive
    private Double weight;

    @NotNull
    @Positive
    private Double height;

    @NotNull
    @PastOrPresent
    private Instant incubated;

    @NotNull
    @Positive
    @Max(180)
    private Integer revisionPeriod;

    @NotBlank
    @Size(min = 3, max = 255)
    private String status;

    public void setIncubated(Long millis) {
        incubated = Instant.ofEpochMilli(millis);
    }

    public DinosaurPassport toDinosaurPassport(){
        DinosaurPassport dinosaurPassport = new DinosaurPassport();
        setBaseFields(dinosaurPassport);
        dinosaurPassport.setDinosaurName(dinosaurName);
        dinosaurPassport.setDinosaurType(new DinosaurType(dinosaurTypeId));
        dinosaurPassport.setWeight(weight);
        dinosaurPassport.setHeight(height);
        dinosaurPassport.setIncubated(incubated);
        dinosaurPassport.setRevisionPeriod(revisionPeriod);
        dinosaurPassport.setStatus(status);
        return dinosaurPassport;
    }
}
