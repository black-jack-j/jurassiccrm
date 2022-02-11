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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class AviaryPassport extends Document implements ScheduleSource {

    @ManyToOne
    private AviaryType aviaryType;

    @Column(unique = true, nullable = false)
    private String code;

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

    public LocalDate getNextRevisionDate() {

        LocalDate now = LocalDate.now();

        long daysElapsed = ChronoUnit.DAYS.between(builtDate, now);

        long daysUntilNextRevision = daysElapsed % revisionPeriod;

        return now.plusDays(daysUntilNextRevision);
    }

    public List<LocalDate> getNextRevisionDates(int revisionsNum) {
        if (revisionsNum < 1) {
            throw new IllegalArgumentException("At least one revision date should be requested");
        }

        List<LocalDate> nextRevisions = new ArrayList<>();
        final LocalDate baseRevision = getNextRevisionDate();
        nextRevisions.add(baseRevision);
        for (int i = 1; i < revisionsNum; i++) {
            nextRevisions.add(baseRevision.plusDays(i * revisionPeriod));
        }
        return nextRevisions;
    }
}
