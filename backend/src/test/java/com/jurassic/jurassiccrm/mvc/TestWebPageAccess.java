package com.jurassic.jurassiccrm.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jurassic.jurassiccrm.accesscontroll.JurassicUserDetailsService;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.controller.TaskController;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.dto.validation.TaskTOValidator;
import com.jurassic.jurassiccrm.task.dto.validation.exception.TaskValidationException;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.service.TaskService;
import com.jurassic.jurassiccrm.task.util.EntitiesUtil;
import com.jurassic.jurassiccrm.wiki.WikiController;
import com.jurassic.jurassiccrm.wiki.repository.WikiRepository;
import com.jurassic.jurassiccrm.wiki.service.WikiPagesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({WikiController.class, TaskController.class})
@MockBean({
        JurassicUserDetailsService.class,
        WikiPagesService.class,
        WikiRepository.class,
        TaskTOValidator.class,
        TaskBuilder.class
})
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG)
public class TestWebPageAccess {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private TaskBuilder taskBuilder;

    @Test
    public void testLoginPageIsAccessedWithoutAuthentication_then200() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    public void testWikiHomePageIsAccessedWithoutAuthentication_then200() throws Exception {
        mockMvc.perform(get("/wiki/")).andExpect(status().isOk());
    }

    @Test
    public void testTaskCreateAPIAccessedWithoutAuthentication_then401() throws Exception {
        mockMvc.perform(post("/api/task/INCUBATION").with(csrf())).andExpect(status().isUnauthorized());
    }

    @Test
    public void testTaskCreateAPIAccessed_thenTaskTOReturned() throws Exception {
        TaskTO taskTO = TaskTO.builder().id(42L).name("test").taskType(TaskType.INCUBATION).build();
        ObjectMapper mapper = new ObjectMapper();
        String expectedResponse = mapper.writeValueAsString(taskTO);
        when(taskService.createTask(any(User.class), any(TaskTO.class))).thenReturn(taskTO);

        mockMvc.perform(post("/api/task/INCUBATION").with(
                user(EntitiesUtil.getUserDetails("test_writer", "test", Role.TASK_WRITER))
        ).with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(expectedResponse))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedResponse));
    }

    @Test
    public void testTaskCreateAPIAccessWithException_then400Returned() throws Exception {
        TaskTO taskTO = TaskTO.builder().id(42L).name("test").taskType(TaskType.INCUBATION).build();
        ObjectMapper mapper = new ObjectMapper();
        String expectedResponse = mapper.writeValueAsString(taskTO);
        when(taskService.createTask(any(User.class), any(TaskTO.class))).thenThrow(TaskValidationException.class);

        mockMvc.perform(post("/api/task/INCUBATION").with(
                user(EntitiesUtil.getUserDetails("test_writer", "test", Role.TASK_WRITER))
        ).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedResponse))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }


}
