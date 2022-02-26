package com.jurassic.jurassiccrm.mvc;

import com.jurassic.jurassiccrm.accesscontroll.JurassicUserDetailsService;
import com.jurassic.jurassiccrm.logging.service.LogService;
import com.jurassic.jurassiccrm.task.builder.TaskBuilder;
import com.jurassic.jurassiccrm.task.controller.TaskController;
import com.jurassic.jurassiccrm.task.service.TaskService;
import com.jurassic.jurassiccrm.wiki.WikiController;
import com.jurassic.jurassiccrm.wiki.repository.WikiRepository;
import com.jurassic.jurassiccrm.wiki.service.WikiPagesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({WikiController.class, TaskController.class})
@MockBean({
        JurassicUserDetailsService.class,
        WikiPagesService.class,
        WikiRepository.class,
        TaskBuilder.class,
        LogService.class,
        TaskService.class
})
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG)
public class TestWebPageAccess {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginPageIsAccessedWithoutAuthentication_then200() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    public void testWikiHomePageIsAccessedWithoutAuthentication_then200() throws Exception {
        mockMvc.perform(get("/wiki/home")).andExpect(status().isOk());
    }

    @Test
    public void testTaskCreateAPIAccessedWithoutAuthentication_then401() throws Exception {
        mockMvc.perform(post("/api/task/INCUBATION").with(csrf())).andExpect(status().isUnauthorized());
    }
}
