package com.jurassic.jurassiccrm.document.model;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name = "test";

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "last_updater", nullable = false)
    private User lastUpdater;

    @Column(nullable = false)
    private Instant created;

    @Column(nullable = false)
    private Instant lastUpdate;

    private String description;

    public Document(DocumentType type) {
        this.type = type;
    }
}
