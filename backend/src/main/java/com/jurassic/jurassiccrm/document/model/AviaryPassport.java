package com.jurassic.jurassiccrm.document.model;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.schedule.model.ScheduleSource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class AviaryPassport extends Document implements ScheduleSource {

    @ManyToOne
    private AviaryType aviaryType;

    @Column(unique = true, nullable = false)
    private Long code;

    @Column(nullable = false)
    private LocalDate builtDate;

    @Column(nullable = false)
    private Integer revisionPeriod;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long square;

    @Override
    public LocalDate getScheduleStartDate() {
        return this.getBuiltDate();
    }

    @Override
    public String getScheduleItemName() {
        return this.getCode().toString();
    }

    @Override
    public Integer getSchedulePeriod() {
        return this.getRevisionPeriod();
    }

    public AviaryPassport() {
        super(DocumentType.AVIARY_PASSPORT);
    }
}
