package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.research.entity.Research;
import com.jurassic.jurassiccrm.research.entity.ResearchData;
import com.jurassic.jurassiccrm.research.repository.ResearchDataRepository;
import com.jurassic.jurassiccrm.research.repository.ResearchesRepository;
import com.jurassic.jurassiccrm.species.repository.SpeciesRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class ResearchDataRepositoryTest {

    @Autowired
    ResearchDataRepository researchDataRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResearchesRepository researchesRepository;

    private static final String RESEARCH_NAME = "Test research";
    private static final String USERNAME = "Test user";

    @BeforeAll
    public static void init(@Autowired UserRepository userRepository, @Autowired ResearchesRepository researchesRepository) {
        User user = new User();
        user.setUsername(USERNAME);
        userRepository.save(user);

        User researcher1 = new User();
        researcher1.setUsername("researcher 1");
        User researcher2 = new User();
        researcher2.setUsername("researcher 2");
        Set<User> researchers = new HashSet<>(userRepository.saveAll(Arrays.asList(researcher1, researcher2)));

        Research research = new Research();
        research.setName(RESEARCH_NAME);
        research.setGoal("some goal");
        research.setResearchers(researchers);
        researchesRepository.save(research);
    }

    @AfterAll
    public static void cleanup(
            @Autowired UserRepository userRepository,
            @Autowired SpeciesRepository speciesRepository,
            @Autowired ResearchDataRepository researchDataRepository) {
        userRepository.deleteAll();
        speciesRepository.deleteAll();
        researchDataRepository.deleteAll();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(1)
    public void testResearchDataCreation() {
        Research research = researchesRepository.findByName(RESEARCH_NAME).orElse(null);
        User user = userRepository.findByUsername(USERNAME).orElse(null);
        byte[] attachment = new byte[] {0x1, 0x2, 0x3};

        ResearchData researchData = new ResearchData();
        researchData.setName("test");
        researchData.setDescription("test");
        researchData.setAuthor(user);
        researchData.setCreated(new Timestamp(System.currentTimeMillis()));
        researchData.setLastUpdater(user);
        researchData.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        researchData.setDescription("testDesc");
        researchData.setResearch(research);
        researchData.setAttachment(attachment);
        researchDataRepository.saveAndFlush(researchData);

        List<ResearchData> foundResearchData = researchDataRepository.findAll();
        assert researchData.getType() == DocumentType.RESEARCH_DATA;
        assert foundResearchData.contains(researchData);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(2)
    public void testResearchDataDeletion() {
        ResearchData researchData = researchDataRepository.findAll().stream().filter(d -> d.getResearch().getName().equals(RESEARCH_NAME)).collect(Collectors.toList()).get(0);
        researchDataRepository.delete(researchData);
        assert !(researchDataRepository.findAll().contains(researchData));
    }
}
