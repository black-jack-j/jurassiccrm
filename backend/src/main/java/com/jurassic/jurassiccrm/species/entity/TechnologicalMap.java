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

    @ElementCollection
    @CollectionTable(name = "incubation_steps")
    @OrderColumn
    private List<String> incubationSteps = new ArrayList<>();

    public boolean addIncubationStep(String step){
        return incubationSteps.add(step);
    }

    public void insertIncubationStep(int index, String step){
        incubationSteps.add(index, step);
    }

    public boolean removeIncubationStep(String step){
        return incubationSteps.remove(step);
    }

    public void removeIncubationStep(int index){
        incubationSteps.remove(index);
    }

    @ElementCollection
    @CollectionTable(name = "egg_creation_steps")
    @OrderColumn
    private List<String> eggCreationSteps = new ArrayList<>();

    public boolean addEggCreationStep(String step){
        return eggCreationSteps.add(step);
    }

    public void insertEggCreationStep(int index, String step){
        eggCreationSteps.add(index, step);
    }

    public boolean removeEggCreationStep(String step){
        return eggCreationSteps.remove(step);
    }

    public void removeEggCreationStep(int index){
        eggCreationSteps.remove(index);
    }

    public TechnologicalMap() {
        super(DocumentType.TECHNOLOGICAL_MAP);
    }
}
