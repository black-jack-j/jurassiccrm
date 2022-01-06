package com.jurassic.jurassiccrm.species.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Data
@Entity
@Getter
@Setter
@IdClass(IncubationPK.class)
public class IncubationSteps {

    @Id
    @Column(nullable = false)
    private Long order_;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private TechnologicalMap technologicalMap;

    private String step;

//    public void setTechnologicalMap(TechnologicalMap map) {
//        if (incubation == null) incubation = new IncubationPK();
//        incubation.setTechnologicalMap(map);
//    }
//
//    public void setOrder(Long order) {
//        if (incubation == null) incubation = new IncubationPK();
//        incubation.setOrder_(order);
//    }
//
//    public Long getOrder() {
//        return incubation.getOrder_();
//    }
//
//    public TechnologicalMap getTechnologicalMap() {
//        return incubation.getTechnologicalMap();
//    }
}
