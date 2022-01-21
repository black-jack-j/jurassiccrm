package com.jurassic.jurassiccrm.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ScheduleInputTO {
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate maxDate;
}
