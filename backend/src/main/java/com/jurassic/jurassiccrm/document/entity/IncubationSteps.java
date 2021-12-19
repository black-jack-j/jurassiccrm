package com.jurassic.jurassiccrm.document.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Data
@Entity
@Getter
@Setter
public class IncubationSteps {

    @EmbeddedId
    private IncubationPK incubation;

    private String step;
}
