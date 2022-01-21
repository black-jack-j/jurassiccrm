package com.jurassic.jurassiccrm.schedule.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ScheduleSource {
    LocalDate getScheduleStartDate();

    String getScheduleItemName();

    Integer getSchedulePeriod();

    default List<ScheduleItem> generateFutureScheduleItems(Integer number) {
        if (getSchedulePeriod() <= 0)
            return new ArrayList<>();
        List<ScheduleItem> scheduleItems = new ArrayList<>();
        LocalDate scheduleDate = getFirstScheduleDateAfterOrEqualToday();
        for (int i = 0; i < number; i++) {
            scheduleItems.add(new ScheduleItem(scheduleDate, getScheduleItemName()));
            scheduleDate = scheduleDate.plusDays(getSchedulePeriod());
        }
        return scheduleItems;
    }

    default LocalDate getFirstScheduleDateAfterOrEqualToday() {
        LocalDate currentDate = getScheduleStartDate();
        LocalDate todayDate = LocalDate.now();
        while (currentDate.isBefore(todayDate))
            currentDate = currentDate.plusDays(getSchedulePeriod());
        return currentDate;
    }
}
