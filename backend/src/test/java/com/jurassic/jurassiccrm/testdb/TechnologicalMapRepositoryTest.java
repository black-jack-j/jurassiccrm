package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.species.model.Species;
import com.jurassic.jurassiccrm.species.model.TechnologicalMap;
import com.jurassic.jurassiccrm.species.repository.SpeciesRepository;
import com.jurassic.jurassiccrm.species.repository.TechnologicalMapRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

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

        speciesRepository.save(new Species(SPECIES_NAME));
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

        technologicalMap.addIncubationStep("Incubation step 1");
        technologicalMap.addIncubationStep("Incubation step 2");

        technologicalMap.addEggCreationStep("Egg creation step 1");
        technologicalMap.addEggCreationStep("Egg creation step 2");

        technologicalMapRepository.save(technologicalMap);

        List<TechnologicalMap> foundTechMaps = technologicalMapRepository.findAll();
        assert technologicalMap.getType() == DocumentType.TECHNOLOGICAL_MAP;
        assert foundTechMaps.contains(technologicalMap);
        assert foundTechMaps.get(0).getIncubationSteps().size() == 2;
        assert foundTechMaps.get(0).getEggCreationSteps().size() == 2;
    }

    @Test
    void updateBaseDocumentFieldsOfTechnologicalMap(){
        testTechnologicalMapCreation();
        val technologicalMap = technologicalMapRepository.findAll().get(0);

        val newName = "New name";
        technologicalMap.setName(newName);
        technologicalMapRepository.save(technologicalMap);

        val found = technologicalMapRepository.findAll();
        assert found.size() == 1;
        assert found.get(0).getName().equals(newName);
    }

    @Test
    void updateSpeciesOfTechnologicalMap(){
        testTechnologicalMapCreation();
        val technologicalMap = technologicalMapRepository.findAll().get(0);

        val newSpecies = speciesRepository.save(new Species("New species"));
        technologicalMap.setSpecies(newSpecies);
        technologicalMapRepository.save(technologicalMap);

        val found = technologicalMapRepository.findAll();
        assert found.size() == 1;
        assert found.get(0).getSpecies().getName().equals(newSpecies.getName());
    }

    @Test
    void failUpdateSpeciesOfTechnologicalMapIfNewSpeciesDoesNotExist(){
        testTechnologicalMapCreation();
        val technologicalMap = technologicalMapRepository.findAll().get(0);

        technologicalMap.setSpecies(new Species("New species"));
        try {
            technologicalMapRepository.saveAndFlush(technologicalMap);
            fail("Should have thrown");
        } catch (Exception t){}
    }

    @Test
    void addIncubationAndEggCreationSteps(){
        testTechnologicalMapCreation();
        val technologicalMap = technologicalMapRepository.findAll().get(0);

        val newStep = "New step";
        technologicalMap.addIncubationStep(newStep);
        technologicalMap.addEggCreationStep(newStep);
        technologicalMapRepository.save(technologicalMap);

        val found = technologicalMapRepository.findAll();
        assert found.size() == 1;
        val foundMap = found.get(0);
        assert foundMap.getIncubationSteps().size() == 3;
        assert foundMap.getEggCreationSteps().size() == 3;
        assert foundMap.getIncubationSteps().get(2).equals(newStep);
        assert foundMap.getEggCreationSteps().get(2).equals(newStep);
    }

    @Test
    void deleteIncubationAndEggCreationSteps(){
        testTechnologicalMapCreation();
        val technologicalMap = technologicalMapRepository.findAll().get(0);

        technologicalMap.removeIncubationStep(0);
        technologicalMap.removeEggCreationStep(0);
        technologicalMapRepository.save(technologicalMap);

        val found = technologicalMapRepository.findAll();
        assert found.size() == 1;
        val foundMap = found.get(0);
        assert foundMap.getIncubationSteps().size() == 1;
        assert foundMap.getEggCreationSteps().size() == 1;
        assert foundMap.getIncubationSteps().get(0).equals(technologicalMap.getIncubationSteps().get(0));
        assert foundMap.getEggCreationSteps().get(0).equals(technologicalMap.getEggCreationSteps().get(0));
    }

    @Test
    void reorderIncubationAndEggCreationSteps(){
        testTechnologicalMapCreation();
        val technologicalMap = technologicalMapRepository.findAll().get(0);

        val incubationSteps = technologicalMap.getIncubationSteps();
        val eggCreationSteps = technologicalMap.getEggCreationSteps();
        technologicalMap.setIncubationSteps(new LinkedList<>(Arrays.asList(incubationSteps.get(1), incubationSteps.get(0))));
        technologicalMap.setEggCreationSteps(new LinkedList<>(Arrays.asList(eggCreationSteps.get(1), eggCreationSteps.get(0))));
        technologicalMapRepository.save(technologicalMap);

        val found = technologicalMapRepository.findAll();
        assert found.size() == 1;
        val foundMap = found.get(0);
        assert foundMap.getIncubationSteps().size() == 2;
        assert foundMap.getEggCreationSteps().size() == 2;
        assert foundMap.getIncubationSteps().get(0).equals(incubationSteps.get(1));
        assert foundMap.getIncubationSteps().get(1).equals(incubationSteps.get(0));
        assert foundMap.getEggCreationSteps().get(0).equals(eggCreationSteps.get(1));
        assert foundMap.getEggCreationSteps().get(1).equals(eggCreationSteps.get(0));
    }

    @Test
    void renameIncubationAndEggCreationSteps(){
        testTechnologicalMapCreation();
        val technologicalMap = technologicalMapRepository.findAll().get(0);

        val newStep = "New step";
        technologicalMap.removeIncubationStep(0);
        technologicalMap.insertIncubationStep(0, newStep);
        technologicalMap.removeEggCreationStep(0);
        technologicalMap.insertEggCreationStep(0, newStep);
        technologicalMapRepository.save(technologicalMap);

        val found = technologicalMapRepository.findAll();
        assert found.size() == 1;
        val foundMap = found.get(0);
        assert foundMap.getIncubationSteps().size() == 2;
        assert foundMap.getEggCreationSteps().size() == 2;
        assert foundMap.getIncubationSteps().get(0).equals(newStep);
        assert foundMap.getEggCreationSteps().get(0).equals(newStep);
        assert foundMap.getIncubationSteps().get(1).equals(technologicalMap.getIncubationSteps().get(1));
        assert foundMap.getEggCreationSteps().get(1).equals(technologicalMap.getEggCreationSteps().get(1));
    }
}
