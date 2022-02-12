package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.dao.TechnologicalMapRepository;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.document.model.TechnologicalMap;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
class TechnologicalMapRepositoryTest {

    @Autowired
    TechnologicalMapRepository technologicalMapRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DinosaurTypeRepository dinosaurTypeRepository;

    private static final String DINOSAUR_TYPE_NAME = "Test dinosaur type";
    private static final String USERNAME = "Test user";

    @BeforeEach
    public void init() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword("");
        userRepository.save(user);

        dinosaurTypeRepository.save(new DinosaurType(DINOSAUR_TYPE_NAME));
    }

    @Test
    public void testTechnologicalMapCreation() {
        DinosaurType dinosaurType = dinosaurTypeRepository.findByName(DINOSAUR_TYPE_NAME).orElse(null);
        User user = userRepository.findByUsername(USERNAME).orElse(null);

        TechnologicalMap technologicalMap = new TechnologicalMap();
        technologicalMap.setName("test");
        technologicalMap.setDescription("test");
        technologicalMap.setAuthor(user);
        technologicalMap.setCreated(Instant.now());
        technologicalMap.setLastUpdater(user);
        technologicalMap.setLastUpdate(Instant.now());
        technologicalMap.setDinosaurType(dinosaurType);
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
    void updateDinosaurTypeOfTechnologicalMap(){
        testTechnologicalMapCreation();
        val technologicalMap = technologicalMapRepository.findAll().get(0);

        val newDinosaurType = dinosaurTypeRepository.save(new DinosaurType("New dinosaur type"));
        technologicalMap.setDinosaurType(newDinosaurType);
        technologicalMapRepository.save(technologicalMap);

        val found = technologicalMapRepository.findAll();
        assert found.size() == 1;
        assert found.get(0).getDinosaurType().getName().equals(newDinosaurType.getName());
    }

    @Test
    void failUpdateDinosaurTypeOfTechnologicalMapIfNewDinosaurTypeDoesNotExist(){
        testTechnologicalMapCreation();
        val technologicalMap = technologicalMapRepository.findAll().get(0);

        technologicalMap.setDinosaurType(new DinosaurType("New dinosaur type"));
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
