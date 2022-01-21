package com.jurassic.jurassiccrm.document.model;

import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.schedule.model.ScheduleSource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class DinosaurPassport extends Document implements ScheduleSource {

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
    private LocalDate incubated;

    @Column(nullable = false)
    private Integer revisionPeriod;

    @Column(nullable = false)
    private String status;

    @Override
    public LocalDate getScheduleStartDate() {
        return this.getIncubated();
    }

    @Override
    public String getScheduleItemName() {
        return this.getDinosaurName();
    }

    @Override
    public Integer getSchedulePeriod() {
        return this.getRevisionPeriod();
    }

    public DinosaurPassport() {
        super(DocumentType.DINOSAUR_PASSPORT);
    }
}
