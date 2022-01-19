package com.jurassic.jurassiccrm.testcontrollers;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureMockMvc
public class ResearchControllerTest extends SimpleEntityControllerTest {

    protected ResearchControllerTest() {
        super("/api/research");
    }
}
