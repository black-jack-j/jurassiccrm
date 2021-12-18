package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.species.entity.Species;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@Getter
@Setter
public class ThemeZoneDinosaurs {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ThemeZoneProject themeZone;

    @ManyToOne
    private Species specie;

    private Long number;

}
