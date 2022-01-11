package com.jurassic.jurassiccrm.species.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
@IdClass(IncubationPK.class)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class IncubationSteps {

    @Id
    @Column(nullable = false)
    private Long order_;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TechnologicalMap technologicalMap;

    @ToString.Include
    public long technologicalMapId(){
        return Optional.ofNullable(technologicalMap.getId()).orElse(0L);
    }

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
