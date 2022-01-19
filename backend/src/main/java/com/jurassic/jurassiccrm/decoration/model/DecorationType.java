package com.jurassic.jurassiccrm.decoration.model;

import com.jurassic.jurassiccrm.common.model.SimpleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class DecorationType implements SimpleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;

    public DecorationType(String name) {
        this.name = name;
    }

    public DecorationType(Long id) {
        this.id = id;
    }
}
