package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.themezone.entity.primarykey.ThemeZoneDecorationsPK;
import lombok.*;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ThemeZoneProject themeZone;

    @ToString.Include
    private Long themeZoneId(){
        return themeZone.getId();
    }

    @Id
    @Enumerated(value = EnumType.STRING)
    private DecorationTypes decorationType;

    @Column(nullable = false)
    private Long number;
}
