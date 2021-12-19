package com.jurassic.jurassiccrm.document.entity;


import com.jurassic.jurassiccrm.species.entity.Species;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@Getter
@Setter
public class TechnologicalMap {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Document baseDocument;

    @ManyToOne
    private Species species;

}
