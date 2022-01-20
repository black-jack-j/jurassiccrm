package com.jurassic.jurassiccrm.dinosaur.model;

import com.jurassic.jurassiccrm.common.model.SimpleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class DinosaurType implements SimpleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;

    public DinosaurType(String name) {
        this.name = name;
    }

    public DinosaurType(Long id) {
        this.id = id;
    }
}
