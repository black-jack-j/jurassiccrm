package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.species.entity.IncubationSteps;
import com.jurassic.jurassiccrm.species.entity.TechnologicalMap;
import com.jurassic.jurassiccrm.species.repository.TechnologicalMapRepository;
import com.jurassic.jurassiccrm.species.entity.Species;
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
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class TechnologicalMapRepositoryTest {

    @Autowired
    TechnologicalMapRepository technologicalMapRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpeciesRepository speciesRepository;

    private static final String SPECIES_NAME = "Test species";
    private static final String USERNAME = "Test user";

    @BeforeAll
    public static void init(@Autowired UserRepository userRepository, @Autowired SpeciesRepository speciesRepository) {
        User user = new User();
        user.setUsername(USERNAME);
        userRepository.save(user);

        Species species = new Species();
        species.setName(SPECIES_NAME);
        speciesRepository.save(species);
    }

    @AfterAll
    public static void cleanup(
            @Autowired UserRepository userRepository,
            @Autowired SpeciesRepository speciesRepository,
            @Autowired TechnologicalMapRepository technologicalMapRepository) {
        userRepository.deleteAll();
        speciesRepository.deleteAll();
        technologicalMapRepository.deleteAll();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(1)
    public void testTechnologicalMapCreation() {
        Species species = speciesRepository.findByName(SPECIES_NAME).orElse(null);
        User user = userRepository.findByUsername(USERNAME).orElse(null);

        TechnologicalMap technologicalMap = new TechnologicalMap();
        technologicalMap.setName("test");
        technologicalMap.setDescription("test");
        technologicalMap.setAuthor(user);
        technologicalMap.setCreated(new Timestamp(System.currentTimeMillis()));
        technologicalMap.setLastUpdater(user);
        technologicalMap.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        technologicalMap.setSpecies(species);
        technologicalMap.setDescription("testDesc");

        IncubationSteps step1 = new IncubationSteps();
        step1.setOrder_(1L);
        step1.setTechnologicalMap(technologicalMap);
        step1.setStep("step 1");

        IncubationSteps step2 = new IncubationSteps();
        step2.setOrder_(2L);
        step2.setTechnologicalMap(technologicalMap);
        step2.setStep("step 2");

        technologicalMap.setIncubationSteps(Arrays.asList(step1, step2));

        technologicalMapRepository.save(technologicalMap);

        List<TechnologicalMap> foundTechMaps = technologicalMapRepository.findAll();
        assert technologicalMap.getType() == DocumentType.TECHNOLOGICAL_MAP;
        assert foundTechMaps.contains(technologicalMap);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(2)
    public void testTechnologicalMapDeletion() {
        TechnologicalMap technologicalMap = technologicalMapRepository.findAll().stream().filter(d -> d.getSpecies().getName().equals("Test species")).collect(Collectors.toList()).get(0);
        technologicalMapRepository.delete(technologicalMap);
        assert !(technologicalMapRepository.findAll().contains(technologicalMap));
    }
}
