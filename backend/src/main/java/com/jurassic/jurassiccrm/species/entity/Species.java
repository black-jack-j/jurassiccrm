package com.jurassic.jurassiccrm.species.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Getter
@Setter
public class Species {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

}
