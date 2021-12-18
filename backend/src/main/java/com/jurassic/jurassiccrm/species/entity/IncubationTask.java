package com.jurassic.jurassiccrm.species.entity;

import com.jurassic.jurassiccrm.task.entity.Task;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@Getter
@Setter
public class IncubationTask {

    @Id
    private Long id;

    @ManyToOne
    private Task task;

    @ManyToOne
    private Species species;
}
