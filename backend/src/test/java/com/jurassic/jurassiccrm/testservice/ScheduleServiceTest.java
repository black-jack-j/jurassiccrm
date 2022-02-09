package com.jurassic.jurassiccrm.testservice;

import com.jurassic.jurassiccrm.accesscontroll.model.Department;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.dao.AviaryPassportRepository;
import com.jurassic.jurassiccrm.document.dao.DinosaurPassportRepository;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import com.jurassic.jurassiccrm.document.model.DinosaurPassport;
import com.jurassic.jurassiccrm.schedule.service.ScheduleService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@DataJpaTest
public class ScheduleServiceTest {
    private final AviaryPassportRepository aviaryPassportRepository;
    private final DinosaurPassportRepository dinosaurPassportRepository;
    private final DinosaurTypeRepository dinosaurTypeRepository;
    private final AviaryTypeRepository aviaryTypeRepository;
    private final UserRepository userRepository;
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleServiceTest(AviaryPassportRepository aviaryPassportRepository, DinosaurPassportRepository dinosaurPassportRepository, DinosaurTypeRepository dinosaurTypeRepository, AviaryTypeRepository aviaryTypeRepository, UserRepository userRepository) {
        this.aviaryPassportRepository = aviaryPassportRepository;
        this.dinosaurPassportRepository = dinosaurPassportRepository;
        this.dinosaurTypeRepository = dinosaurTypeRepository;
        this.aviaryTypeRepository = aviaryTypeRepository;
        this.userRepository = userRepository;
        this.scheduleService = new ScheduleService(aviaryPassportRepository, dinosaurPassportRepository);
    }

    @Test
    @Transactional
    void generateEmptyScheduleIfNoScheduleSourcesWasCreated() {
        val schedule = scheduleService.getScheduleForEmployeeUntilDate(LocalDate.now().plusDays(10), createMaintainer());
        Assertions.assertTrue(schedule.isEmpty());
    }

    @Test
    @Transactional
    void generateAviaryScheduleItemsForSavedScheduleSources() {
        val source = createAviaryPassport(1);
        val schedule = scheduleService.getScheduleForEmployeeUntilDate(LocalDate.now(), createMaintainer());
        Assertions.assertEquals(1, schedule.size());
        Assertions.assertEquals(source.getCode().toString(), schedule.get(0).getName());
    }

    @Test
    @Transactional
    void generateDinosaurScheduleItemsForSavedScheduleSources() {
        val source = createDinosaurPassport(1);
        val schedule = scheduleService.getScheduleForEmployeeUntilDate(LocalDate.now(), createAccommodation());
        Assertions.assertEquals(1, schedule.size());
        Assertions.assertEquals(source.getDinosaurName(), schedule.get(0).getName());
    }

    @Test
    @Transactional
    void dontGenerateAviaryScheduleItemsForDinosaurPassports() {
        createDinosaurPassport(1);
        val schedule = scheduleService.getScheduleForEmployeeUntilDate(LocalDate.now(), createMaintainer());
        Assertions.assertTrue(schedule.isEmpty());
    }

    @Test
    @Transactional
    void dontGenerateDinosaurScheduleItemsForAviaryPassports() {
        createAviaryPassport(1);
        val schedule = scheduleService.getScheduleForEmployeeUntilDate(LocalDate.now(), createAccommodation());
        Assertions.assertTrue(schedule.isEmpty());
    }

    @Test
    @Transactional
    void generateAviaryScheduleItemsForSavedScheduleSourcesSortedByDate() {
        val source1 = createAviaryPassport(LocalDate.now().minusDays(3), 4);
        val source2 = createAviaryPassport(LocalDate.now().minusDays(2), 4);
        val schedule = scheduleService.getScheduleForEmployeeUntilDate(LocalDate.now().plusDays(2), createMaintainer());
        Assertions.assertEquals(2, schedule.size());
        Assertions.assertEquals(source1.getCode().toString(), schedule.get(0).getName());
        Assertions.assertEquals(source2.getCode().toString(), schedule.get(1).getName());
    }

    private DinosaurPassport createDinosaurPassport() {
        return createDinosaurPassport(1);
    }

    private DinosaurPassport createDinosaurPassport(Integer period) {
        return createDinosaurPassport(LocalDate.now(), period);
    }

    private DinosaurPassport createDinosaurPassport(LocalDate startDate, Integer period) {
        val user = new User("name" + period + startDate);
        val type = dinosaurTypeRepository.save(new DinosaurType("type" + period + startDate));
        val savedUser = userRepository.save(user);
        DinosaurPassport scheduleSource = new DinosaurPassport();

        scheduleSource.setStatus("status");
        scheduleSource.setHeight(1.0);
        scheduleSource.setWeight(1.0);
        scheduleSource.setName("name " + period + startDate);
        scheduleSource.setDinosaurType(type);
        scheduleSource.setAuthor(savedUser);
        scheduleSource.setLastUpdater(savedUser);
        scheduleSource.setCreated(LocalDateTime.now());
        scheduleSource.setLastUpdate(LocalDateTime.now());

        scheduleSource.setDinosaurName("Dinosaur" + period + startDate.getDayOfYear());
        scheduleSource.setIncubated(startDate);
        scheduleSource.setRevisionPeriod(period);
        return dinosaurPassportRepository.saveAndFlush(scheduleSource);
    }

    private AviaryPassport createAviaryPassport(Integer period) {
        return createAviaryPassport(LocalDate.now(), period);
    }

    private AviaryPassport createAviaryPassport(LocalDate startDate) {
        return createAviaryPassport(startDate, 1);
    }

    private AviaryPassport createAviaryPassport(LocalDate startDate, Integer period) {
        val user = new User("name" + period + startDate);
        val type = aviaryTypeRepository.save(new AviaryType("type" + period + startDate));
        val savedUser = userRepository.save(user);
        AviaryPassport scheduleSource = new AviaryPassport();

        scheduleSource.setStatus("status");
        scheduleSource.setName("name " + period + startDate);
        scheduleSource.setAuthor(savedUser);
        scheduleSource.setLastUpdater(savedUser);
        scheduleSource.setCreated(LocalDateTime.now());
        scheduleSource.setLastUpdate(LocalDateTime.now());
        scheduleSource.setAviaryType(type);
        scheduleSource.setSquare(1L);

        scheduleSource.setCode(""+111L + period + startDate.getDayOfYear());
        scheduleSource.setBuiltDate(startDate);
        scheduleSource.setRevisionPeriod(period);
        return aviaryPassportRepository.saveAndFlush(scheduleSource);
    }

    private User createMaintainer() {
        User user = new User();
        user.setDepartment(Department.MAINTENANCE);
        return user;
    }

    private User createAccommodation() {
        User user = new User();
        user.setDepartment(Department.ACCOMMODATION);
        return user;
    }
}
