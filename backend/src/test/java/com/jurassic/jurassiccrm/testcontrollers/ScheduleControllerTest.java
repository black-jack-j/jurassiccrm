package com.jurassic.jurassiccrm.testcontrollers;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.document.dao.AviaryPassportRepository;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    AviaryTypeRepository aviaryTypeRepository;
    @Autowired
    AviaryPassportRepository aviaryPassportRepository;
    @Autowired
    UserRepository userRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String SCHEDULE_URL = "/api/schedule";
    private static final String AVIARY_SCHEDULE_URL = "/api/schedule/aviary";
    private static final String DEFAULT_DATE = LocalDate.now().plusDays(10).format(formatter);
    private static final String EMPTY_DATE_JSON = "{\"maxDate\":\"\"}";
    private static final String WRONG_DATE_FORMAT_JSON = "{\"maxDate\":\"01-01-2021\"}";
    private static final String NULL_DATE_JSON = "{\"maxDate\":null}";
    private static final String NO_DATE_JSON = "{\"no-maxDate\":\"" + DEFAULT_DATE + "\"}";
    private static final String DEFAULT_JSON = "{\"maxDate\":\"" + DEFAULT_DATE + "\"}";
    private static final String ADMIN_USERNAME = "admin";
    private static final String MAINTENANCE_USERNAME = "test-maintenance";

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnEmptyAviaryPassportSchedule() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AVIARY_SCHEDULE_URL)
                        .content(DEFAULT_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.[0]").doesNotExist());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnAviaryRevisionScheduleForOneAviaryPassport() throws Exception {
        AviaryPassport aviaryPassport = createAviaryPassport(LocalDate.now().minusDays(1), 10);
        mockMvc.perform(MockMvcRequestBuilders.post(AVIARY_SCHEDULE_URL)
                        .content(DEFAULT_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.[0].name").value(aviaryPassport.getCode().toString()))
                .andExpect(jsonPath("$.[0].date").value(aviaryPassport.getBuiltDate().plusDays(10).format(formatter)))
                .andExpect(jsonPath("$.[1]").doesNotExist());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnEmptyAviaryPassportScheduleIfUserIsNotFromAccommodation() throws Exception {
        createAviaryPassport(LocalDate.now().minusDays(1), 10);
        mockMvc.perform(MockMvcRequestBuilders.post(SCHEDULE_URL)
                        .content(DEFAULT_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.[0]").doesNotExist());
    }

    @Test
    @Transactional
    @WithUserDetails(MAINTENANCE_USERNAME)
    void returnAviaryRevisionScheduleForOneAviaryPassportIfUserIsFromAccommodation() throws Exception {
        AviaryPassport aviaryPassport = createAviaryPassport(LocalDate.now().minusDays(1), 10);
        mockMvc.perform(MockMvcRequestBuilders.post(SCHEDULE_URL)
                        .content(DEFAULT_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.[0].name").value(aviaryPassport.getCode().toString()))
                .andExpect(jsonPath("$.[0].date").value(aviaryPassport.getBuiltDate().plusDays(10).format(formatter)))
                .andExpect(jsonPath("$.[1]").doesNotExist());

    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400IfDateIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AVIARY_SCHEDULE_URL)
                        .content(NULL_DATE_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400IfThereIsNoDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AVIARY_SCHEDULE_URL)
                        .content(NO_DATE_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400IfDateIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AVIARY_SCHEDULE_URL)
                        .content(EMPTY_DATE_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400IfDateIsInWrongFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AVIARY_SCHEDULE_URL)
                        .content(WRONG_DATE_FORMAT_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
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
}
