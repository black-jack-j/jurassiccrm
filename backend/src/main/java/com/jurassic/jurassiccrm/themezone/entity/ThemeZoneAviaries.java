package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.themezone.entity.primarykey.ThemeZoneAviariesPK;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(ThemeZoneAviariesPK.class)
public class ThemeZoneAviaries {
    @Id
    @ManyToOne
    @JoinColumn
    private ThemeZoneProject themeZone;

    @Id
    @Enumerated(value = EnumType.STRING)
    private AviaryTypes aviaryType;

    @Column(nullable = false)
    private Long number;
}
