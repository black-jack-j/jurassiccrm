package com.jurassic.jurassiccrm.species.entity;


import com.jurassic.jurassiccrm.document.entity.Document;
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

    @OneToMany(mappedBy = "technologicalMap")
    private List<IncubationSteps> incubationSteps;
}
