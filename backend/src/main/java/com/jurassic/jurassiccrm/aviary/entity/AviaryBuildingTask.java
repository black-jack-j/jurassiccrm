package com.jurassic.jurassiccrm.aviary.entity;

import com.jurassic.jurassiccrm.task.entity.Task;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
public class AviaryBuildingTask {
    @Id
    @GeneratedValue()
    private Long id;

    @ManyToOne
    private Task baseTask;

    @Enumerated(value = EnumType.STRING)
    private AviaryTypes aviaryType;

}
