package com.jurassic.jurassiccrm.themezone.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
public class ThemeZoneDecorations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ThemeZoneProject themeZone;

    @Enumerated(value = EnumType.STRING)
    private DecorationTypes decorationType;

    private Long number;
}
