package com.jurassic.jurassiccrm.species.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Species {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

}
