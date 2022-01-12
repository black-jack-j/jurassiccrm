package com.jurassic.jurassiccrm.species.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Species {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(unique = true)
    private String name;

}
