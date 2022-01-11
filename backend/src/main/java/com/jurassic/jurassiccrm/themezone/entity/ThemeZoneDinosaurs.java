package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.species.entity.Species;
import com.jurassic.jurassiccrm.themezone.entity.primarykey.ThemeZoneDinosaursPK;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ThemeZoneDinosaursPK.class)
public class ThemeZoneDinosaurs {
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
    @ManyToOne
    @JoinColumn
    private Species specie;

    @Column(nullable = false)
    private Long number;

}
