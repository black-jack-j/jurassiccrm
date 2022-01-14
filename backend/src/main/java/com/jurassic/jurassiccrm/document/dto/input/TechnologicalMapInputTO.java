package com.jurassic.jurassiccrm.document.dto.input;

import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.model.TechnologicalMap;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TechnologicalMapInputTO extends DocumentInputTO {
    @NotNull
    private Long dinosaurTypeId;

    @NotEmpty
    @Size(max = 25)
    private List<@NotBlank String> incubationSteps = new ArrayList<>();

    @NotEmpty
    @Size(max = 25)
    private List<@NotBlank String> eggCreationSteps = new ArrayList<>();

    public TechnologicalMap toTechnologicalMap(){
        TechnologicalMap technologicalMap = new TechnologicalMap();
        setBaseFields(technologicalMap);
        technologicalMap.setDinosaurType(new DinosaurType(dinosaurTypeId));
        technologicalMap.setIncubationSteps(incubationSteps);
        technologicalMap.setEggCreationSteps(eggCreationSteps);
        return technologicalMap;
    }
}
