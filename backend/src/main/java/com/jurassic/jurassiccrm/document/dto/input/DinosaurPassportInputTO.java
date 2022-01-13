package com.jurassic.jurassiccrm.document.dto.input;

import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.model.DinosaurPassport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class DinosaurPassportInputTO extends DocumentInputTO{
    @NotBlank
    private Long dinosaurTypeId;

    @NotBlank
    @Size(min = 3, max=255)
    private String dinosaurName;

    @NotBlank
    @Positive
    private Double weight;

    @NotBlank
    @Positive
    private Double height;

    @NotBlank
    private LocalDateTime incubated;

    @NotBlank
    @Positive
    @Max(180)
    private Integer revisionPeriod;

    @NotBlank
    @Size(min = 3, max = 255)
    private String status;

    public DinosaurPassport toDinosaurPassport(){
        DinosaurPassport dinosaurPassport = new DinosaurPassport();
        setBaseFields(dinosaurPassport);
        dinosaurPassport.setDinosaurName(dinosaurName);
        dinosaurPassport.setDinosaurType(new DinosaurType(dinosaurTypeId));
        dinosaurPassport.setWeight(weight);
        dinosaurPassport.setHeight(height);
        dinosaurPassport.setIncubated(Timestamp.valueOf(incubated));
        dinosaurPassport.setRevisionPeriod(revisionPeriod);
        dinosaurPassport.setStatus(status);
        return dinosaurPassport;
    }
}
