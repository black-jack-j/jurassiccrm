package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.species.entity.Species;
import com.jurassic.jurassiccrm.themezone.entity.primarykey.ThemeZoneDinosaursPK;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(ThemeZoneDinosaursPK.class)
public class ThemeZoneDinosaurs {
    @Id
    @ManyToOne
    @JoinColumn
    private ThemeZoneProject themeZone;

    @Id
    @ManyToOne
    @JoinColumn
    private Species specie;

    @Column(nullable = false)
    private Long number;

}
