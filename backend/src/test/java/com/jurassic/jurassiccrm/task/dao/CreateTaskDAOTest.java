package com.jurassic.jurassiccrm.task.dao;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.RoleRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.RoleService;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.aviary.CreateAviaryTask;
import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;
import com.jurassic.jurassiccrm.task.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@ComponentScan(basePackages = {"com.jurassic.jurassiccrm.task.service"})
@MockBean(classes = {
        RoleService.class,
        RoleRepository.class
})
public class CreateTaskDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AviaryTypeRepository aviaryTypeRepository;

    @Autowired
    private TaskPriorityRepository taskPriorityRepository;

    @Autowired
    private TaskService taskService;

    private User creator;
    private AviaryType aviaryType;
    private TaskPriority priority;


    @BeforeEach
    private void setup() {
        creator = setupUser();
        creator = userRepository.save(creator);

        aviaryType = setupAviaryType();
        aviaryType = aviaryTypeRepository.save(aviaryType);

        priority = setupPriority();
        priority = taskPriorityRepository.save(priority);
    }

    private User setupUser() {
        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        return user;
    }

    private AviaryType setupAviaryType() {
        AviaryType aviaryType = new AviaryType();
        aviaryType.setName("TEST");
        return aviaryType;
    }

    private TaskPriority setupPriority() {
        TaskPriority priority = new TaskPriority();
        priority.setAbsolutePriority(10L);
        priority.setName("Urgent");
        return priority;
    }

    @Test
    public void testCreateAviaryTaskCanBeSaved() {
        CreateAviaryTask aviaryTask = new CreateAviaryTask("test", creator, aviaryType);
        aviaryTask.setPriority(priority);
        aviaryTask = taskService.createTask(aviaryTask, creator);
        Optional<Task> savedTask = taskRepository.findById(aviaryTask.getId());
        Assertions.assertTrue(savedTask.isPresent());
    }

    @Test
    public void testResearchTaskCanBeSaved() {
        ResearchTask researchTask = new ResearchTask();
        researchTask.setName("Test");
        researchTask = taskService.createTask(researchTask, creator);
        Optional<Task> savedTask = taskRepository.findById(researchTask.getId());
        Assertions.assertTrue(savedTask.isPresent());
    }

    @Test
    public void testIncubationTaskCanBeSaved() {
        IncubationTask incubationTask = new IncubationTask();
        incubationTask.setName("test");
        incubationTask = taskService.createTask(incubationTask, creator);
        Optional<Task> savedTask = taskRepository.findById(incubationTask.getId());
        Assertions.assertTrue(savedTask.isPresent());
    }
}
