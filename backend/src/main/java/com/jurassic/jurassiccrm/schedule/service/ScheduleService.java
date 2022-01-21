package com.jurassic.jurassiccrm.schedule.service;

import com.jurassic.jurassiccrm.accesscontroll.model.Department;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.document.dao.AviaryPassportRepository;
import com.jurassic.jurassiccrm.document.dao.DinosaurPassportRepository;
import com.jurassic.jurassiccrm.schedule.model.ScheduleItem;
import com.jurassic.jurassiccrm.schedule.model.ScheduleSource;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final AviaryPassportRepository aviaryPassportRepository;
    private final DinosaurPassportRepository dinosaurPassportRepository;

    @Autowired
    public ScheduleService(AviaryPassportRepository aviaryPassportRepository, DinosaurPassportRepository dinosaurPassportRepository) {
        this.aviaryPassportRepository = aviaryPassportRepository;
        this.dinosaurPassportRepository = dinosaurPassportRepository;
    }

    public List<ScheduleItem> getAviaryRevisionScheduleUntilDate(LocalDate maxDate) {
        val aviaryPassports = aviaryPassportRepository.findAll();
        return createScheduleFromBunchOfItems(aviaryPassports, maxDate);
    }

    public List<ScheduleItem> getDinosaurRevisionScheduleUntilDate(LocalDate maxDate) {
        val dinosaurPassports = dinosaurPassportRepository.findAll();
        return createScheduleFromBunchOfItems(dinosaurPassports, maxDate);
    }

    public List<ScheduleItem> getScheduleForEmployeeUntilDate(LocalDate maxDate, User employee) {
        if (employee.getDepartment() == Department.ACCOMMODATION)
            return getDinosaurRevisionScheduleUntilDate(maxDate);
        if (employee.getDepartment() == Department.MAINTENANCE)
            return getAviaryRevisionScheduleUntilDate(maxDate);
        return new ArrayList<>();
    }

    private List<ScheduleItem> createScheduleFromBunchOfItems(List<? extends ScheduleSource> scheduleSources, LocalDate maxDate) {
        return scheduleSources.stream()
                .flatMap(source -> source.generateFutureScheduleItems(maxDate).stream())
                .sorted(Comparator.comparing(ScheduleItem::getDate))
                .collect(Collectors.toList());
    }
}
