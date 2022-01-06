package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.themezone.entity.primarykey.ThemeZoneDecorationsPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
