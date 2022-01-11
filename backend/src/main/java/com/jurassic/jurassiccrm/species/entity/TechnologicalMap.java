package com.jurassic.jurassiccrm.species.entity;


import com.jurassic.jurassiccrm.document.entity.Document;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class TechnologicalMap extends Document {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Species species;

    @OneToMany(mappedBy = "technologicalMap", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = IncubationSteps.class)
    private Set<IncubationSteps> incubationSteps = new HashSet<>();

    public boolean addStep(IncubationSteps step){
        return incubationSteps.add(step);
    }

    public boolean addStep(Long order, String step){
        return addStep(new IncubationSteps(order, this, step));
    }

    public boolean removeStep(IncubationSteps step){
        return incubationSteps.remove(step);
    }

    public boolean removeStep(Long order){
        return incubationSteps.removeIf(s -> Objects.equals(order, s.getOrder_()));
    }

    public TechnologicalMap() {
        super(DocumentType.TECHNOLOGICAL_MAP);
    }
}
