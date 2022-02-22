package com.jurassic.jurassiccrm.document.model;

import com.jurassic.jurassiccrm.controller.to.RevisableEntity;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class DinosaurPassport extends Document implements RevisableEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private DinosaurType dinosaurType;

    @Column(nullable = false)
    private String dinosaurName;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Instant incubated;

    @Column(nullable = false)
    private Integer revisionPeriod;

    @Column(nullable = false)
    private String status;

    public DinosaurPassport() {
        super(DocumentType.DINOSAUR_PASSPORT);
    }

    @Override
    public Instant getNextRevisionDate() {
        Instant now = Instant.now();

        long daysElapsed = ChronoUnit.DAYS.between(incubated, now);

        long daysUntilNextRevision = daysElapsed % revisionPeriod;

        return now.plus(daysUntilNextRevision, ChronoUnit.DAYS);
    }
}
