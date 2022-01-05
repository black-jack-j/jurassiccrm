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
    @JoinColumn(nullable = false)
    private ThemeZoneProject themeZone;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private DecorationTypes decorationType;

    @Column(nullable = false)
    private Long number;
}
