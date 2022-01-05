package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
public class ThemeZoneAviaries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ThemeZoneProject themeZone;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private AviaryTypes aviaryType;

    @Column(nullable = false)
    private Long number;
}
