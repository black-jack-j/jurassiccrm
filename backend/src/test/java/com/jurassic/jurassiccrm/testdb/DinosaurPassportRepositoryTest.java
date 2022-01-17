package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.dao.DinosaurPassportRepository;
import com.jurassic.jurassiccrm.document.model.DinosaurPassport;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    DinosaurTypeRepository dinosaurTypeRepository;

    private static final String DINOSAUR_TYPE_NAME = "Test dinosaur type";
    private static final String USERNAME = "Test user";

    @BeforeEach
    public void init() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword("");
        userRepository.save(user);

        DinosaurType dinosaurType = new DinosaurType();
        dinosaurType.setName(DINOSAUR_TYPE_NAME);
        dinosaurTypeRepository.save(dinosaurType);
    }

    @Test
    public void testDinosaurPassportCreation() {
        DinosaurType dinosaurType = dinosaurTypeRepository.findByName(DINOSAUR_TYPE_NAME).orElse(null);
        User user = userRepository.findByUsername(USERNAME).orElse(null);

        DinosaurPassport dinosaurPassport = new DinosaurPassport();
        dinosaurPassport.setName("test");
        dinosaurPassport.setDescription("test");
        dinosaurPassport.setAuthor(user);
        dinosaurPassport.setCreated(LocalDateTime.now());
        dinosaurPassport.setLastUpdater(user);
        dinosaurPassport.setLastUpdate(LocalDateTime.now());
        dinosaurPassport.setDinosaurType(dinosaurType);
        dinosaurPassport.setDinosaurName("test");
        dinosaurPassport.setWeight(123.0);
        dinosaurPassport.setHeight(321.0);
        dinosaurPassport.setDescription("testDesc");
        dinosaurPassport.setIncubated(LocalDate.now());
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
