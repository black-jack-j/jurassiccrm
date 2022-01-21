package com.jurassic.jurassiccrm.testservice;

import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import com.jurassic.jurassiccrm.document.model.DinosaurPassport;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ScheduleSourceTest {

    @Test
    void generateEmptyNoScheduleIfSchedulePeriodIsNegative() {
        AviaryPassport scheduleSource = createAviaryPassport(-1);
        Assertions.assertTrue(scheduleSource.generateFutureScheduleItems(10).isEmpty());
    }

    @Test
    void generateEmptyNoScheduleIfSchedulePeriodIsZero() {
        AviaryPassport scheduleSource = createAviaryPassport(0);
        Assertions.assertTrue(scheduleSource.generateFutureScheduleItems(10).isEmpty());
    }

    @Test
    void generateTheSameAmountOfScheduleItemsThatRequested() {
        AviaryPassport scheduleSource = createAviaryPassport(1);
        Assertions.assertEquals(10, scheduleSource.generateFutureScheduleItems(10).size());
    }

    @Test
    void generateOnlyFutureOrTodayScheduleItems() {
        AviaryPassport scheduleSource = createAviaryPassport(1);
        LocalDate today = LocalDate.now();
        Assertions.assertTrue(scheduleSource.generateFutureScheduleItems(10).stream()
                .noneMatch(i -> i.getDate().isBefore(today)));
    }

    @Test
    void generateOnlyFutureOrTodayScheduleItemsIfStartDateIsInThePast() {
        AviaryPassport scheduleSource = createAviaryPassport(LocalDate.now().minusDays(10));
        LocalDate today = LocalDate.now();
        Assertions.assertTrue(scheduleSource.generateFutureScheduleItems(10).stream()
                .noneMatch(i -> i.getDate().isBefore(today)));
    }

    @Test
    void generateTheSameAmountOfScheduleItemsThatRequestedIfStartDateIsInThePast() {
        AviaryPassport scheduleSource = createAviaryPassport(LocalDate.now().minusDays(10));
        Assertions.assertEquals(10, scheduleSource.generateFutureScheduleItems(10).size());
    }

    @Test
    void setScheduleItemNameEqualToAviaryCode() {
        AviaryPassport scheduleSource = createAviaryPassport(1);
        Assertions.assertTrue(scheduleSource.generateFutureScheduleItems(10).stream()
                .allMatch(i -> i.getName().equals(scheduleSource.getCode().toString())));
    }

    @Test
    void setScheduleItemNameEqualToDinosaurName() {
        DinosaurPassport scheduleSource = createDinosaurPassport();
        Assertions.assertTrue(scheduleSource.generateFutureScheduleItems(10).stream()
                .allMatch(i -> i.getName().equals(scheduleSource.getDinosaurName())));
    }

    @Test
    void generateScheduleItemsWithPeriodEqualToRevisionPeriod() {
        AviaryPassport scheduleSource = createAviaryPassport(10);
        val scheduleItems = scheduleSource.generateFutureScheduleItems(3);
        val firstItemDate = scheduleItems.get(0).getDate();
        val secondItemDate = scheduleItems.get(1).getDate();
        val thirdItemDate = scheduleItems.get(2).getDate();
        Assertions.assertEquals(10, firstItemDate.until(secondItemDate, ChronoUnit.DAYS));
        Assertions.assertEquals(10, secondItemDate.until(thirdItemDate, ChronoUnit.DAYS));
    }

    @Test
    void generateFirstScheduleItemAtIntegerNumberOfPeriodsFromStartDateButNotFromToday() {
        val startDate = LocalDate.now().minusDays(5);
        val today = LocalDate.now();
        AviaryPassport scheduleSource = createAviaryPassport(startDate, 10);
        val scheduleItems = scheduleSource.generateFutureScheduleItems(1);
        val itemDate = scheduleItems.get(0).getDate();
        Assertions.assertEquals(5, today.until(itemDate, ChronoUnit.DAYS));
    }

    @Test
    void generateFirstScheduleItemAtSomeNumberOfPeriodsFromStartDate() {
        val startDate = LocalDate.now().minusDays(25);
        val today = LocalDate.now();
        AviaryPassport scheduleSource = createAviaryPassport(startDate, 10);
        val scheduleItems = scheduleSource.generateFutureScheduleItems(1);
        val itemDate = scheduleItems.get(0).getDate();
        Assertions.assertEquals(5, today.until(itemDate, ChronoUnit.DAYS));
    }

    @Test
    void useRevisionPeriodFromDinosaurPassportAsPeriodForSchedule() {
        DinosaurPassport scheduleSource = createDinosaurPassport(10);
        val scheduleItems = scheduleSource.generateFutureScheduleItems(2);
        val firstItemDate = scheduleItems.get(0).getDate();
        val secondItemDate = scheduleItems.get(1).getDate();
        Assertions.assertEquals(10, firstItemDate.until(secondItemDate, ChronoUnit.DAYS));
    }

    @Test
    void useIncubatedFromDinosaurPassportAsStartDateForSchedule() {
        val startDate = LocalDate.now().minusDays(5);
        DinosaurPassport scheduleSource = createDinosaurPassport(startDate, 10);
        val scheduleItems = scheduleSource.generateFutureScheduleItems(1);
        val itemDate = scheduleItems.get(0).getDate();
        Assertions.assertEquals(startDate, itemDate.minusDays(10));
    }

    @Test
    void generateEmptyScheduleIfEndDateIsBeforeStartDate() {
        val startDate = LocalDate.now();
        val endDate = LocalDate.now().minusDays(1);
        val scheduleSource = createAviaryPassport(startDate);
        val scheduleItems = scheduleSource.generateFutureScheduleItems(endDate);
        Assertions.assertTrue(scheduleItems.isEmpty());
    }

    @Test
    void generateEmptyScheduleIfEndDateIsAfterStartDateButBeforeToday() {
        val startDate = LocalDate.now().minusDays(2);
        val endDate = LocalDate.now().minusDays(1);
        val scheduleSource = createAviaryPassport(startDate);
        val scheduleItems = scheduleSource.generateFutureScheduleItems(endDate);
        Assertions.assertTrue(scheduleItems.isEmpty());
    }

    @Test
    void generateOneScheduleItemIfEndDateIsTodayAndStartDateIsBeforeToday() {
        val startDate = LocalDate.now().minusDays(1);
        val endDate = LocalDate.now();
        val scheduleSource = createAviaryPassport(startDate);
        val scheduleItems = scheduleSource.generateFutureScheduleItems(endDate);
        Assertions.assertEquals(1, scheduleItems.size());
    }

    @Test
    void doNotGenerateScheduleItemsAfterMaxDate() {
        val startDate = LocalDate.now();
        val endDate = LocalDate.now().plusDays(5);
        val scheduleSource = createAviaryPassport(startDate, 10);
        val scheduleItems = scheduleSource.generateFutureScheduleItems(endDate);
        Assertions.assertEquals(1, scheduleItems.size());
        Assertions.assertTrue(scheduleItems.get(0).getDate().isBefore(endDate));
    }

    @Test
    void generateEnoughScheduleItemsToCoverGapBetweenStartDateAndEndDate() {
        val startDate = LocalDate.now();
        val endDate = LocalDate.now().plusDays(15);
        val scheduleSource = createAviaryPassport(startDate, 10);
        val scheduleItems = scheduleSource.generateFutureScheduleItems(endDate);
        Assertions.assertEquals(2, scheduleItems.size());
    }

    private DinosaurPassport createDinosaurPassport() {
        return createDinosaurPassport(1);
    }

    private DinosaurPassport createDinosaurPassport(Integer period) {
        return createDinosaurPassport(LocalDate.now(), period);
    }

    private DinosaurPassport createDinosaurPassport(LocalDate startDate, Integer period) {
        DinosaurPassport scheduleSource = new DinosaurPassport();
        scheduleSource.setDinosaurName("Dinosaur");
        scheduleSource.setIncubated(startDate);
        scheduleSource.setRevisionPeriod(period);
        return scheduleSource;
    }

    private AviaryPassport createAviaryPassport(Integer period) {
        return createAviaryPassport(LocalDate.now(), period);
    }

    private AviaryPassport createAviaryPassport(LocalDate startDate) {
        return createAviaryPassport(startDate, 1);
    }

    private AviaryPassport createAviaryPassport(LocalDate startDate, Integer period) {
        AviaryPassport scheduleSource = new AviaryPassport();
        scheduleSource.setCode(111L);
        scheduleSource.setBuiltDate(startDate);
        scheduleSource.setRevisionPeriod(period);
        return scheduleSource;
    }
}
