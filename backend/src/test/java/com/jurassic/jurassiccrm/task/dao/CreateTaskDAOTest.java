package com.jurassic.jurassiccrm.task.dao;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.aviary.CreateAviaryTask;
import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;
import com.jurassic.jurassiccrm.task.util.EntitiesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
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

    private User creator;
    private AviaryType aviaryType;
    private TaskPriority priority;


    @BeforeEach
    private void setup() {
        creator = EntitiesUtil.getUser("test", "test");
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
        CreateAviaryTask task = new CreateAviaryTask("test", creator);
        task.setPriority(priority);
        setTaskTimestamps(task);
        task = taskRepository.saveAndFlush(task);
        Optional<Task> savedTask = taskRepository.findById(task.getId());
        Assertions.assertTrue(savedTask.isPresent());
    }

    @Test
    public void testResearchTaskCanBeSaved() {
        ResearchTask task = new ResearchTask("test", creator);
        setTaskTimestamps(task);
        task.setPriority(priority);
        task = taskRepository.saveAndFlush(task);
        Optional<Task> savedTask = taskRepository.findById(task.getId());
        Assertions.assertTrue(savedTask.isPresent());
    }

    @Test
    public void testIncubationTaskCanBeSaved() {
        IncubationTask task = new IncubationTask("test", creator);
        setTaskTimestamps(task);
        task.setPriority(priority);
        task = taskRepository.saveAndFlush(task);
        Optional<Task> savedTask = taskRepository.findById(task.getId());
        Assertions.assertTrue(savedTask.isPresent());
    }

    private void setTaskTimestamps(Task task) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        task.setLastUpdated(timestamp);
        task.setCreated(timestamp);
    }
}
