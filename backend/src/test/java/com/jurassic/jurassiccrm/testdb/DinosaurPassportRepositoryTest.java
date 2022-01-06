package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.species.entity.DinosaurPassport;
import com.jurassic.jurassiccrm.species.entity.Species;
import com.jurassic.jurassiccrm.species.repository.DinosaurPassportRepository;
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
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class DinosaurPassportRepositoryTest {

    @Autowired
    DinosaurPassportRepository dinosaurPassportRepository;

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
            @Autowired DinosaurPassportRepository dinosaurPassportRepository) {
        userRepository.deleteAll();
        speciesRepository.deleteAll();
        dinosaurPassportRepository.deleteAll();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(1)
    public void testDinosaurPassportCreation() {
        Species species = speciesRepository.findByName(SPECIES_NAME).orElse(null);
        User user = userRepository.findByUsername(USERNAME).orElse(null);

        DinosaurPassport dinosaurPassport = new DinosaurPassport();
        dinosaurPassport.setName("test");
        dinosaurPassport.setDescription("test");
        dinosaurPassport.setAuthor(user);
        dinosaurPassport.setCreated(new Timestamp(System.currentTimeMillis()));
        dinosaurPassport.setLastUpdater(user);
        dinosaurPassport.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        dinosaurPassport.setSpecies(species);
        dinosaurPassport.setDinosaurName("test");
        dinosaurPassport.setWeight(123.0);
        dinosaurPassport.setHeight(321.0);
        dinosaurPassport.setDescription("testDesc");
        dinosaurPassport.setIncubated(new Timestamp(System.currentTimeMillis()));
        dinosaurPassport.setRevisionPeriod(1);
        dinosaurPassport.setStatus("Done");
        dinosaurPassportRepository.save(dinosaurPassport);

        List<DinosaurPassport> foundDinosaurPassport = dinosaurPassportRepository.findAll();
        assert dinosaurPassport.getType() == DocumentType.DINOSAUR_PASSPORT;
        assert foundDinosaurPassport.contains(dinosaurPassport);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(2)
    public void testDinosaurPassportDeletion() {
        DinosaurPassport dinosaurPassport = dinosaurPassportRepository.findAll().stream().filter(d -> d.getDinosaurName().equals("test")).collect(Collectors.toList()).get(0);
        dinosaurPassportRepository.delete(dinosaurPassport);
        assert !(dinosaurPassportRepository.findAll().contains(dinosaurPassport));
    }
}
