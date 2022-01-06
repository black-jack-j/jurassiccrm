package com.jurassic.jurassiccrm.themezone.entity.primarykey;

import com.jurassic.jurassiccrm.species.entity.Species;
import com.jurassic.jurassiccrm.themezone.entity.ThemeZoneProject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class ThemeZoneDinosaursPK implements Serializable {
    private ThemeZoneProject themeZone;
    private Species specie;
}
