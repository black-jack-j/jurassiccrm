package com.jurassic.jurassiccrm.document.dto.output.document;

import com.jurassic.jurassiccrm.document.dto.output.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.document.model.TechnologicalMap;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TechnologicalMapOutputTO extends DocumentOutputTO {
    private SimpleEntityOutputTO dinosaurType;
    private String[] incubationSteps;
    private String[] eggCreationSteps;

    public static TechnologicalMapOutputTO fromDocument(TechnologicalMap document){
        TechnologicalMapOutputTO dto = new TechnologicalMapOutputTO();
        dto.setBaseFields(document);
        dto.setDinosaurType(SimpleEntityOutputTO.fromEntity(document.getDinosaurType()));
        dto.setIncubationSteps(document.getIncubationSteps().toArray(new String[0]));
        dto.setEggCreationSteps(document.getEggCreationSteps().toArray(new String[0]));
        return dto;
    }
}
