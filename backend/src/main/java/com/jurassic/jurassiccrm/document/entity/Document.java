package com.jurassic.jurassiccrm.document.entity;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name = "test";

    @Column(nullable = false)
    private String type = "TestType";

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "last_updater", nullable = false)
    private User lastUpdater;

    @Column(nullable = false)
    private Timestamp created;

    @Column(nullable = false)
    private Timestamp lastUpdate;

    private String description;
}
