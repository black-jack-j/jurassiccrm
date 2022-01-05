package com.jurassic.jurassiccrm.document.entity;


import com.jurassic.jurassiccrm.species.entity.Species;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class TechnologicalMap extends Document {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Species species;

    @OneToMany
    private List<IncubationSteps> incubationSteps;
}
