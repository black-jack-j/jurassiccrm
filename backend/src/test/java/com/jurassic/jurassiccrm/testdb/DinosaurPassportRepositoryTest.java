package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.species.model.DinosaurPassport;
import com.jurassic.jurassiccrm.species.model.Species;
import com.jurassic.jurassiccrm.species.repository.DinosaurPassportRepository;
import com.jurassic.jurassiccrm.species.repository.SpeciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
    public void testDinosaurPassportUpdate(){
        testDinosaurPassportCreation();
        DinosaurPassport passport = dinosaurPassportRepository.findAll().get(0);
        passport.setName("Updated");
        passport.setRevisionPeriod(666);
        dinosaurPassportRepository.save(passport);
        List<DinosaurPassport> foundPassports = dinosaurPassportRepository.findAll();
        assert foundPassports.size() == 1;
        assert foundPassports.get(0).getName().equals("Updated");
        assert foundPassports.get(0).getRevisionPeriod() == 666;
    }
}
