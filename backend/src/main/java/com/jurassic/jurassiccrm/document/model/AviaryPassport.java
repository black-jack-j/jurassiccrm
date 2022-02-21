package com.jurassic.jurassiccrm.document.model;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.controller.to.RevisableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class AviaryPassport extends Document implements RevisableEntity {

    @ManyToOne
    private AviaryType aviaryType;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private Instant builtDate;

    @Column(nullable = false)
    private Integer revisionPeriod;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long square;

    public AviaryPassport() {
        super(DocumentType.AVIARY_PASSPORT);
    }

    @Override
    public Instant getNextRevisionDate() {

        Instant now = Instant.now();

        long daysElapsed = ChronoUnit.DAYS.between(builtDate, now);

        long daysUntilNextRevision = daysElapsed % revisionPeriod;

        return now.plus(daysUntilNextRevision, ChronoUnit.DAYS);
    }

}
