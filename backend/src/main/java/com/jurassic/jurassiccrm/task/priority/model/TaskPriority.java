package com.jurassic.jurassiccrm.task.priority.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class TaskPriority implements Comparable<TaskPriority> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public TaskPriority(Long absolutePriority, String name) {
        this.absolutePriority = absolutePriority;
        this.name = name;
    }

    @Column(nullable = false, unique = true)
    private Long absolutePriority;

    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public int compareTo(TaskPriority that) {
        return this.absolutePriority.compareTo(that.absolutePriority);
    }
}
