package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.entity.AviaryPassport;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.species.entity.IncubationSteps;
import com.jurassic.jurassiccrm.species.entity.TechnologicalMap;
import com.jurassic.jurassiccrm.species.repository.TechnologicalMapRepository;
import com.jurassic.jurassiccrm.species.entity.Species;
import com.jurassic.jurassiccrm.species.repository.SpeciesRepository;
import org.hibernate.collection.internal.PersistentSet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

    @BeforeEach
    public void init() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword("");
        userRepository.save(user);

        Species species = new Species();
        species.setName(SPECIES_NAME);
        speciesRepository.save(species);
    }

    @Test
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

        technologicalMap.addStep(1L, "Step 1");
        technologicalMap.addStep(2L, "Step 2");

        technologicalMapRepository.save(technologicalMap);

        List<TechnologicalMap> foundTechMaps = technologicalMapRepository.findAll();
        assert technologicalMap.getType() == DocumentType.TECHNOLOGICAL_MAP;
        assert foundTechMaps.contains(technologicalMap);
        assert foundTechMaps.get(0).getIncubationSteps().size() == 2;
    }

    @Test
    public void testTechnologicalMapUpdate(){
        testTechnologicalMapCreation();
        TechnologicalMap technologicalMap = technologicalMapRepository.findAll().get(0);
        System.out.println(technologicalMap);

        IncubationSteps deletedStep = technologicalMap.getIncubationSteps().iterator().next();
        assert technologicalMap.getIncubationSteps().contains(deletedStep);

        IncubationSteps newStep = new IncubationSteps();
        newStep.setOrder_(42L);
        newStep.setStep("UpdatedStep");
        newStep.setTechnologicalMap(technologicalMap);

        technologicalMap.setName("Updated");
        technologicalMap.removeStep(deletedStep);
        technologicalMap.addStep(newStep);
        technologicalMapRepository.save(technologicalMap);
        List<TechnologicalMap> foundMaps = technologicalMapRepository.findAll();
        assert foundMaps.size() == 1;
        assert foundMaps.get(0).getName().equals("Updated");
        assert foundMaps.get(0).getIncubationSteps().contains(newStep);
        assert !foundMaps.get(0).getIncubationSteps().contains(deletedStep);
        assert foundMaps.get(0).getIncubationSteps().size() == 2;
    }
}
