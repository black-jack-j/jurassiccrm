package com.jurassic.jurassiccrm.aviary.model;

import com.jurassic.jurassiccrm.common.model.SimpleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class AviaryType implements SimpleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public AviaryType(String name) {
        this.name = name;
    }

    public AviaryType(Long id) {
        this.id = id;
    }
}
