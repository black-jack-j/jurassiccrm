package com.jurassic.jurassiccrm.document.entity;


import com.jurassic.jurassiccrm.species.entity.Species;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
public class TechnologicalMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Document baseDocument;

    @ManyToOne
    private Species species;

}
