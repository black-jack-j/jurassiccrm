package com.jurassic.jurassiccrm.document.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class IncubationPK implements Serializable {

    private Long order_;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TechnologicalMap technologicalMap;
}
