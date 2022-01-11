package com.jurassic.jurassiccrm.task.entity;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private TaskType taskType;

    private Timestamp created;

    @ManyToOne
    private User createdBy;

    private Timestamp LastUpdated;

    @ManyToOne
    private User LastUpdater;

    private String status;

    private String description;

    @ManyToOne
    private User assignee;
}
