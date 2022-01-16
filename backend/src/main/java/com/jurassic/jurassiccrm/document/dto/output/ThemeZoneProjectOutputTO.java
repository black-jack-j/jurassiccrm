package com.jurassic.jurassiccrm.document.dto.output;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.model.ThemeZoneProject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ThemeZoneProjectOutputTO extends DocumentOutputTO {
    private String projectName;
    private UserOutputTO manager;
    private Map<SimpleEntityOutputTO, Integer> dinosaurs = new HashMap<>();
    private Map<SimpleEntityOutputTO, Integer> aviaries = new HashMap<>();
    private Map<SimpleEntityOutputTO, Integer> decorations = new HashMap<>();

    private void addDecorations(DecorationType type, Integer number){
        decorations.put(SimpleEntityOutputTO.fromEntity(type), number);
    }

    private void addDinosaurs(DinosaurType type, Integer number){
        decorations.put(SimpleEntityOutputTO.fromEntity(type), number);
    }

    private void addAviaries(AviaryType type, Integer number){
        decorations.put(SimpleEntityOutputTO.fromEntity(type), number);
    }

    public static ThemeZoneProjectOutputTO fromDocument(ThemeZoneProject document){
        ThemeZoneProjectOutputTO dto = new ThemeZoneProjectOutputTO();

        dto.setBaseFields(document);
        dto.setProjectName(document.getProjectName());
        dto.setManager(UserOutputTO.fromUser(document.getManager()));
        document.getDecorations().forEach(dto::addDecorations);
        document.getAviaries().forEach(dto::addAviaries);
        document.getDinosaurs().forEach(dto::addDinosaurs);
        return dto;
    }
}
