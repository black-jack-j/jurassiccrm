package com.jurassic.jurassiccrm.dinosaur.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class DinosaurType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;

}
