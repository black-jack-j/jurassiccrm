package com.jurassic.jurassiccrm.schedule.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.schedule.dto.ScheduleInputTO;
import com.jurassic.jurassiccrm.schedule.model.ScheduleItem;
import com.jurassic.jurassiccrm.schedule.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@Api(tags = "schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    @ApiOperation(value = "getScheduleForUser", nickname = "getScheduleForUser")
    public ResponseEntity<List<ScheduleItem>> getSchedule(@RequestBody @Valid ScheduleInputTO input,
                                                          @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        User user = userDetails.getUserInfo();
        List<ScheduleItem> schedule = scheduleService.getScheduleForEmployeeUntilDate(input.getMaxDate(), user);
        return ResponseEntity.ok(schedule);
    }

    @PostMapping("/aviary")
    @ApiOperation(value = "getAviarySchedule", nickname = "getAviarySchedule")
    public ResponseEntity<List<ScheduleItem>> getAviarySchedule(@RequestBody @Valid ScheduleInputTO input) {
        List<ScheduleItem> schedule = scheduleService.getAviaryRevisionScheduleUntilDate(input.getMaxDate());
        return ResponseEntity.ok(schedule);
    }

    @PostMapping("/dinosaur")
    @ApiOperation(value = "getDinosaurSchedule", nickname = "getDinosaurSchedule")
    public ResponseEntity<List<ScheduleItem>> getDinosaurSchedule(@RequestBody @Valid ScheduleInputTO input) {
        List<ScheduleItem> schedule = scheduleService.getDinosaurRevisionScheduleUntilDate(input.getMaxDate());
        return ResponseEntity.ok(schedule);
    }
}
