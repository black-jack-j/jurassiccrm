package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.themezone.entity.primarykey.ThemeZoneAviariesPK;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(ThemeZoneAviariesPK.class)
public class ThemeZoneAviaries {
    @Id
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ThemeZoneProject themeZone;

    @ToString.Include
    private Long themeZoneId(){
        return themeZone.getId();
    }

    @Id
    @Enumerated(value = EnumType.STRING)
    private AviaryTypes aviaryType;

    @Column(nullable = false)
    private Long number;
}
