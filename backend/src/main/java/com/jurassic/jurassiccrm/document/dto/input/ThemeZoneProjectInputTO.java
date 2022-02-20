package com.jurassic.jurassiccrm.document.dto.input;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.model.ThemeZoneProject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ThemeZoneProjectInputTO extends DocumentInputTO {

    @NotBlank
    @Size(min = 3, max = 255)
    private String projectName;

    @NotNull
    private Long managerId;

    @NotEmpty
    @Size(max = 25)
    private Map<Long, Integer> dinosaurs = new HashMap<>();

    @NotEmpty
    @Size(max = 25)
    private Map<Long, Integer> aviaries = new HashMap<>();

    @Size(max = 25)
    private Map<Long, Integer> decorations = new HashMap<>();

    public ThemeZoneProject toDocument(){
        ThemeZoneProject themeZoneProject = new ThemeZoneProject();

        Map<DinosaurType, Integer> projectDinosaurs = new HashMap<>();
        dinosaurs.forEach((key, value) -> projectDinosaurs.put(new DinosaurType(key), value));

        Map<AviaryType, Integer> projectAviaries = new HashMap<>();
        aviaries.forEach((key, value) -> projectAviaries.put(new AviaryType(key), value));

        Map<DecorationType, Integer> projectDecorations = new HashMap<>();
        decorations.forEach((key, value) -> projectDecorations.put(new DecorationType(key), value));

        setBaseFields(themeZoneProject);
        themeZoneProject.setProjectName(projectName);
        themeZoneProject.setManager(new User(managerId));
        themeZoneProject.setDinosaurs(projectDinosaurs);
        themeZoneProject.setAviaries(projectAviaries);
        themeZoneProject.setDecorations(projectDecorations);

        return themeZoneProject;
    }
}
