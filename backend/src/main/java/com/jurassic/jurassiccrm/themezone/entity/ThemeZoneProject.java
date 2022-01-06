package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.document.entity.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
public class ThemeZoneProject extends Document {

    @Column(unique = true, nullable = false)
    private String projectName;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User manager;

    @OneToMany(mappedBy = "themeZone")
    private List<ThemeZoneDinosaurs> dinosaurs;

    @OneToMany(mappedBy = "themeZone")
    private List<ThemeZoneAviaries> aviaries;

    @OneToMany(mappedBy = "themeZone")
    private List<ThemeZoneDecorations> decorations;
}
