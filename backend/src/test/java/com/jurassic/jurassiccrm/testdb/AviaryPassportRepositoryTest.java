package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.entity.AviaryPassport;
import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.aviary.repository.AviaryPassportRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class AviaryPassportRepositoryTest {

    @Autowired
    AviaryPassportRepository aviaryPassportRepository;

    @Autowired
    UserRepository userRepository;

    private static final String USERNAME = "Test user";

    @BeforeAll
    public static void init(@Autowired UserRepository userRepository) {
        User user = new User();
        user.setUsername(USERNAME);
        userRepository.save(user);
    }

    @AfterAll
    public static void cleanup(
            @Autowired UserRepository userRepository,
            @Autowired AviaryPassportRepository aviaryPassportRepository) {
        userRepository.deleteAll();
        aviaryPassportRepository.deleteAll();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(1)
    public void testAviaryPassportCreation(){
        AviaryPassport aviaryPassport = new AviaryPassport();
        aviaryPassport.setName("test");
        aviaryPassport.setType("test");
        aviaryPassport.setDescription("test");
        aviaryPassport.setAuthor(userRepository.findByUsername(USERNAME).orElse(null));
        aviaryPassport.setCreated(new Timestamp(System.currentTimeMillis()));
        aviaryPassport.setLastUpdater(userRepository.findByUsername(USERNAME).orElse(null));
        aviaryPassport.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        aviaryPassport.setAviaryType(AviaryTypes.AVIARY_1);
        aviaryPassport.setCode(1111L);
        aviaryPassport.setDescription("testDesc");
        aviaryPassport.setBuiltDate(new Date(System.currentTimeMillis()));
        aviaryPassport.setRevisionPeriod(1);
        aviaryPassport.setStatus("Done");
        aviaryPassportRepository.save(aviaryPassport);

        List<AviaryPassport> foundAviariesPassport = aviaryPassportRepository.findAll();
        assert foundAviariesPassport.contains(aviaryPassport);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(2)
    public void testAviaryPassportDeletion(){
        AviaryPassport aviaryPassport = aviaryPassportRepository.findByCode(1111L);
        aviaryPassportRepository.delete(aviaryPassport);
        assert !(aviaryPassportRepository.findAll().contains(aviaryPassport));
    }
}
