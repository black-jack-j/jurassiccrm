package com.jurassic.jurassiccrm.document.dto.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.model.DinosaurPassport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class DinosaurPassportInputTO extends DocumentInputTO{
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incubated;

    @NotNull
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
        dinosaurPassport.setIncubated(LocalDateTime.ofInstant(incubated.toInstant(), ZoneId.of("Z")).toLocalDate());
        dinosaurPassport.setRevisionPeriod(revisionPeriod);
        dinosaurPassport.setStatus(status);
        return dinosaurPassport;
    }
}
