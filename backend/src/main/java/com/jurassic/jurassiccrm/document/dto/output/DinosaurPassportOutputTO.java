package com.jurassic.jurassiccrm.document.dto.output;

import com.jurassic.jurassiccrm.document.model.DinosaurPassport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class DinosaurPassportOutputTO extends DocumentOutputTO {
    private SimpleEntityOutputTO dinosaurType;
    private String dinosaurName;
    private Double weight;
    private Double height;
    private LocalDate incubated;
    private Integer revisionPeriod;
    private String status;

    public static DinosaurPassportOutputTO fromDocument(DinosaurPassport dinosaurPassport){
        DinosaurPassportOutputTO dto = new DinosaurPassportOutputTO();
        dto.setBaseFields(dinosaurPassport);
        dto.setDinosaurType(SimpleEntityOutputTO.fromEntity(dinosaurPassport.getDinosaurType()));
        dto.setDinosaurName (dinosaurPassport.getDinosaurName());
        dto.setWeight(dinosaurPassport.getWeight());
        dto.setHeight(dinosaurPassport.getHeight());
        dto.setIncubated(dinosaurPassport.getIncubated());
        dto.setRevisionPeriod(dinosaurPassport.getRevisionPeriod());
        dto.setStatus(dinosaurPassport.getStatus());
        return dto;
    }
}
