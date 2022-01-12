package com.jurassic.jurassiccrm.task.service;

import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.repository.DocumentRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TaskService.class)
@MockBean(classes = {
        DocumentRepository.class,
        UserRepository.class,
})
@ActiveProfiles("test")
public class TaskServiceUnitTest {

}
