package com.jurassic.jurassiccrm.document.dto.output.document;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.common.dto.UserOutputTO;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.dto.output.ThemeZoneElementOutputTO;
import com.jurassic.jurassiccrm.document.model.ThemeZoneProject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ThemeZoneProjectOutputTO extends DocumentOutputTO {
    private String projectName;
    private UserOutputTO manager;
    private List<ThemeZoneElementOutputTO> dinosaurs = new ArrayList<>();
    private List<ThemeZoneElementOutputTO> aviaries = new ArrayList<>();
    private List<ThemeZoneElementOutputTO> decorations = new ArrayList<>();

    private void addDecorations(DecorationType type, Integer number){
        decorations.add(new ThemeZoneElementOutputTO(SimpleEntityOutputTO.fromEntity(type), number));
    }

    private void addDinosaurs(DinosaurType type, Integer number){
        dinosaurs.add(new ThemeZoneElementOutputTO(SimpleEntityOutputTO.fromEntity(type), number));
    }

    private void addAviaries(AviaryType type, Integer number){
        aviaries.add(new ThemeZoneElementOutputTO(SimpleEntityOutputTO.fromEntity(type), number));
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
