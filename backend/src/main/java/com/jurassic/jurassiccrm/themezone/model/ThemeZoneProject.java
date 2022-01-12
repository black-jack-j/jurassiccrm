package com.jurassic.jurassiccrm.themezone.model;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.species.model.Species;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
public class ThemeZoneProject extends Document {

    @Column(unique = true, nullable = false)
    private String projectName;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User manager;

    @ElementCollection
    @CollectionTable(name = "theme_zone_dinosaurs")
    @MapKeyJoinColumn(unique = true)
    private Map<Species, Integer> dinosaurs = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "theme_zone_aviaries")
    @MapKeyJoinColumn(unique = true)
    private Map<AviaryType, Integer> aviaries = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "theme_zone_decorations")
    @MapKeyJoinColumn(unique = true)
    private Map<DecorationType, Integer> decorations = new HashMap<>();

    public void addAviaries(AviaryType type, Integer number){
        aviaries.put(type, number);
    }

    public void removeAviaries(AviaryType type){
        aviaries.remove(type);
    }

    public void addDinosaurs(Species species, Integer number){
        dinosaurs.put(species, number);
    }

    public void removeDinosaurs(Species species){
        dinosaurs.remove(species);
    }

    public void addDecorations(DecorationType type, Integer number){
        decorations.put(type, number);
    }

    public void removeDecorations(DecorationType type){
        decorations.remove(type);
    }

    public ThemeZoneProject() {
        super(DocumentType.THEME_ZONE_PROJECT);
    }
}
