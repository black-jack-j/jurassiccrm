package com.jurassic.jurassiccrm.research.entity;

import com.jurassic.jurassiccrm.species.entity.TechnologicalMap;
import com.jurassic.jurassiccrm.task.entity.Task;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
public class ResearchTask {
    @Id
    @GeneratedValue()
    private Long id;

    @ManyToOne
    private Task baseTask;

    @ManyToOne
    private TechnologicalMap technologicalMap;

    @ManyToOne
    private Research research;

}
