package com.jurassic.jurassiccrm.themezone.entity.primarykey;

import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.themezone.entity.ThemeZoneProject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class ThemeZoneAviariesPK implements Serializable {
    private ThemeZoneProject themeZone;
    private AviaryTypes aviaryType;
}
