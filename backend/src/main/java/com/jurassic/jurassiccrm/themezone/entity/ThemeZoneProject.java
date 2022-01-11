package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.document.entity.Document;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.species.entity.IncubationSteps;
import com.jurassic.jurassiccrm.species.entity.Species;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "themeZone", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = ThemeZoneDinosaurs.class)
    private Set<ThemeZoneDinosaurs> dinosaurs = new HashSet<>();

    @OneToMany(mappedBy = "themeZone", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = ThemeZoneAviaries.class)
    private Set<ThemeZoneAviaries> aviaries = new HashSet<>();

    @OneToMany(mappedBy = "themeZone", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = ThemeZoneDecorations.class)
    private Set<ThemeZoneDecorations> decorations = new HashSet<>();

    public void addAviaries(ThemeZoneAviaries aviaries){
        this.aviaries.add(aviaries);
    }

    public void addAviaries(AviaryTypes type, Long number){
        addAviaries(new ThemeZoneAviaries(this, type, number));
    }

    public void removeAviaries(ThemeZoneAviaries aviaries){
        this.aviaries.remove(aviaries);
    }

    public void removeAviaries(AviaryTypes type){
        aviaries.removeIf(a -> Objects.equals(a.getAviaryType(),type));
    }

    public void addDinosaurs(ThemeZoneDinosaurs dinosaurs){
        this.dinosaurs.add(dinosaurs);
    }

    public void addDinosaurs(Species species, Long number){
        addDinosaurs(new ThemeZoneDinosaurs(this, species, number));
    }

    public void removeDinosaurs(ThemeZoneDinosaurs dinosaurs){
        this.dinosaurs.remove(dinosaurs);
    }

    public void removeDinosaurs(Species species){
        dinosaurs.removeIf(d -> Objects.equals(d.getSpecie(),species));
    }

    public void addDecorations(ThemeZoneDecorations decorations){
        this.decorations.add(decorations);
    }

    public void addDecorations(DecorationTypes type, Long number){
        addDecorations(new ThemeZoneDecorations(this, type, number));
    }

    public void removeDecorations(ThemeZoneDecorations decorations){
        this.decorations.remove(decorations);
    }

    public void removeDecorations(DecorationTypes type){
        decorations.removeIf(d -> Objects.equals(d.getDecorationType(),type));
    }

    public ThemeZoneProject() {
        super(DocumentType.THEME_ZONE_PROJECT);
    }
}
