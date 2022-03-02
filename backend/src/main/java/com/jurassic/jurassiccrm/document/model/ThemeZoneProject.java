package com.jurassic.jurassiccrm.document.model;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
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
    @MapKeyJoinColumn
    private Map<DinosaurType, Integer> dinosaurs = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "theme_zone_aviaries")
    @MapKeyJoinColumn
    private Map<AviaryType, Integer> aviaries = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "theme_zone_decorations")
    @MapKeyJoinColumn
    private Map<DecorationType, Integer> decorations = new HashMap<>();

    public void addAviaries(AviaryType type, Integer number){
        aviaries.put(type, number);
    }

    public void removeAviaries(AviaryType type){
        aviaries.remove(type);
    }

    public void addDinosaurs(DinosaurType dinosaurType, Integer number){
        dinosaurs.put(dinosaurType, number);
    }

    public void removeDinosaurs(DinosaurType dinosaurType){
        dinosaurs.remove(dinosaurType);
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
