package com.jurassic.jurassiccrm.testcontrollers;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureMockMvc
public class DinosaurTypeControllerTest extends SimpleEntityControllerTest {

    protected DinosaurTypeControllerTest() {
        super("/api/dinosaur/type");
    }
}
