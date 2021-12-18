package com.jurassic.jurassiccrm.document.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class IncubationPK implements Serializable {

    private Long order_;

    @ManyToOne
    private TechnologicalMap technologicalMap;
}
