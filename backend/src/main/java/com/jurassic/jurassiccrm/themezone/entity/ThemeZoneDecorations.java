package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.themezone.entity.primarykey.ThemeZoneDecorationsPK;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(ThemeZoneDecorationsPK.class)
public class ThemeZoneDecorations {
    @Id
    @ManyToOne
    @JoinColumn
    private ThemeZoneProject themeZone;

    @Id
    @Enumerated(value = EnumType.STRING)
    private DecorationTypes decorationType;

    @Column(nullable = false)
    private Long number;
}
