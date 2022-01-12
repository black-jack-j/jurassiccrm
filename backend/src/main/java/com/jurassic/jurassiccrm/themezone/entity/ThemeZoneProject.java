package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.document.entity.Document;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.species.entity.Species;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

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
    @MapKeyEnumerated
    private Map<AviaryTypes, Integer> aviaries = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "theme_zone_decorations")
    @MapKeyEnumerated
    private Map<DecorationTypes, Integer> decorations = new HashMap<>();

    public void addAviaries(AviaryTypes type, Integer number){
        aviaries.put(type, number);
    }

    public void removeAviaries(AviaryTypes type){
        aviaries.remove(type);
    }

    public void addDinosaurs(Species species, Integer number){
        dinosaurs.put(species, number);
    }

    public void removeDinosaurs(Species species){
        dinosaurs.remove(species);
    }

    public void addDecorations(DecorationTypes type, Integer number){
        decorations.put(type, number);
    }

    public void removeDecorations(DecorationTypes type){
        decorations.remove(type);
    }

    public ThemeZoneProject() {
        super(DocumentType.THEME_ZONE_PROJECT);
    }
}
