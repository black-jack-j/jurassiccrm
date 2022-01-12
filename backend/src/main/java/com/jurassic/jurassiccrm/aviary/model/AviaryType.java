package com.jurassic.jurassiccrm.aviary.model;

import com.jurassic.jurassiccrm.task.model.aviary.CreateAviaryTask;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AviaryType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "aviaryType")
    private List<CreateAviaryTask> mentionedInCreateAviaryTasks = new ArrayList<>();

}
