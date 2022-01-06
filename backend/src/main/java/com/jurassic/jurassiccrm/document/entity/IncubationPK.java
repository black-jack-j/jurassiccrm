package com.jurassic.jurassiccrm.document.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public class IncubationPK implements Serializable {
    private Long order_;
    private TechnologicalMap technologicalMap;
}
