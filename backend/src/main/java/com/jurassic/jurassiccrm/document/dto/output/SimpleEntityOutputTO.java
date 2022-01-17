package com.jurassic.jurassiccrm.document.dto.output;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.research.model.Research;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleEntityOutputTO {
    private Long id;
    private String name;

    public static SimpleEntityOutputTO fromEntity(DinosaurType dinosaurType){
        return new SimpleEntityOutputTO(dinosaurType.getId(), dinosaurType.getName());
    }

    public static SimpleEntityOutputTO fromEntity(AviaryType aviaryType){
        return new SimpleEntityOutputTO(aviaryType.getId(), aviaryType.getName());
    }

    public static SimpleEntityOutputTO fromEntity(DecorationType decorationType){
        return new SimpleEntityOutputTO(decorationType.getId(), decorationType.getName());
    }

    public static SimpleEntityOutputTO fromEntity(Research research){
        return new SimpleEntityOutputTO(research.getId(), research.getName());
    }
}
