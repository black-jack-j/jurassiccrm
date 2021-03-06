package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.dao.ResearchDataRepository;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.document.model.ResearchData;
import com.jurassic.jurassiccrm.research.dao.ResearchRepository;
import com.jurassic.jurassiccrm.research.model.Research;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
class ResearchDataRepositoryTest {

    @Autowired
    ResearchDataRepository researchDataRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResearchRepository researchRepository;

    private static final String RESEARCH_NAME = "Test research";
    private static final String NEW_RESEARCH_NAME = "New Test research";
    private static final String USERNAME = "Test user";

    @BeforeEach
    public void init() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword("");
        userRepository.save(user);

        User researcher1 = new User();
        researcher1.setUsername("researcher 1");
        researcher1.setPassword("");
        User researcher2 = new User();
        researcher2.setUsername("researcher 2");
        researcher2.setPassword("");
        Set<User> researchers = new HashSet<>(userRepository.saveAll(Arrays.asList(researcher1, researcher2)));

        Research research1 = new Research();
        research1.setName(RESEARCH_NAME);
        research1.setGoal("some goal");
        research1.setResearchers(researchers);
        researchRepository.save(research1);

        Research research2 = new Research();
        research2.setName(NEW_RESEARCH_NAME);
        research2.setGoal("some other goal");
        research2.getResearchers().add(researcher1);
        researchRepository.save(research2);
    }

    @Test
    public void testResearchDataCreation() {
        Research research = researchRepository.findByName(RESEARCH_NAME).orElse(null);
        User user = userRepository.findByUsername(USERNAME).orElse(null);
        byte[] attachment = new byte[] {0x1, 0x2, 0x3};

        ResearchData researchData = new ResearchData();
        researchData.setName("test");
        researchData.setDescription("test");
        researchData.setAuthor(user);
        researchData.setCreated(Instant.now());
        researchData.setLastUpdater(user);
        researchData.setLastUpdate(Instant.now());
        researchData.setDescription("testDesc");
        researchData.setResearch(research);
        researchData.setAttachment(attachment);
        researchData.setAttachmentName("Some file.pdf");
        researchDataRepository.saveAndFlush(researchData);

        List<ResearchData> foundResearchData = researchDataRepository.findAll();
        assert researchData.getType() == DocumentType.RESEARCH_DATA;
        assert foundResearchData.contains(researchData);
    }

    @Test
    public void testResearchDataUpdate(){
        testResearchDataCreation();
        Research newResearch = researchRepository.findByName(NEW_RESEARCH_NAME).orElse(null);
        assert newResearch != null;

        ResearchData passport = researchDataRepository.findAll().get(0);
        passport.setName("Updated");
        passport.setResearch(newResearch);
        researchDataRepository.save(passport);
        List<ResearchData> foundData = researchDataRepository.findAll();

        assert foundData.size() == 1;
        assert foundData.get(0).getName().equals("Updated");
        assert foundData.get(0).getResearch().equals(newResearch);
    }
}
