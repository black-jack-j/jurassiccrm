package com.jurassic.jurassiccrm.themezone.entity.primarykey;

import com.jurassic.jurassiccrm.themezone.entity.DecorationTypes;
import com.jurassic.jurassiccrm.themezone.entity.ThemeZoneProject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class ThemeZoneDecorationsPK implements Serializable {
    private ThemeZoneProject themeZone;
    private DecorationTypes decorationType;
}
