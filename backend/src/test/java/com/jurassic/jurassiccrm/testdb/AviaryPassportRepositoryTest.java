package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.document.dao.AviaryPassportRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.task.util.EntitiesUtil;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class AviaryPassportRepositoryTest {

    @Autowired
    AviaryPassportRepository aviaryPassportRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AviaryTypeRepository aviaryTypeRepository;

    private static final String USERNAME = "Test user";

    @BeforeEach
    public void init() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword("");
        userRepository.save(user);
    }

    @Test
    public void testAviaryPassportCreation(){
        AviaryType aviaryType = EntitiesUtil.getAviaryType("test");
        aviaryType = aviaryTypeRepository.saveAndFlush(aviaryType);

        AviaryPassport aviaryPassport = new AviaryPassport();
        aviaryPassport.setName("test");
        aviaryPassport.setDescription("test");
        aviaryPassport.setAuthor(userRepository.findByUsername(USERNAME).orElse(null));
        aviaryPassport.setCreated(new Timestamp(System.currentTimeMillis()));
        aviaryPassport.setLastUpdater(userRepository.findByUsername(USERNAME).orElse(null));
        aviaryPassport.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        aviaryPassport.setAviaryType(aviaryType);
        aviaryPassport.setCode(1111L);
        aviaryPassport.setDescription("testDesc");
        aviaryPassport.setBuiltDate(new Date(System.currentTimeMillis()));
        aviaryPassport.setRevisionPeriod(1);
        aviaryPassport.setStatus("Done");
        aviaryPassportRepository.save(aviaryPassport);

        List<AviaryPassport> foundAviariesPassport = aviaryPassportRepository.findAll();
        Assertions.assertTrue(foundAviariesPassport.contains(aviaryPassport));
    }

    @Test
    public void testUpdateAviaryPassportBaseFields(){
        testAviaryPassportCreation();
        AviaryPassport passport = aviaryPassportRepository.findAll().get(0);
        passport.setName("Updated");
        aviaryPassportRepository.save(passport);
        List<AviaryPassport> foundPassports = aviaryPassportRepository.findAll();
        Assertions.assertEquals(1, foundPassports.size());
        Assertions.assertEquals("Updated", foundPassports.get(0).getName());
    }

    @Test
    public void testUpdateAviaryPassportPlainSpecificFields(){
        testAviaryPassportCreation();
        AviaryPassport passport = aviaryPassportRepository.findAll().get(0);
        passport.setRevisionPeriod(666);
        aviaryPassportRepository.save(passport);
        List<AviaryPassport> foundPassports = aviaryPassportRepository.findAll();
        Assertions.assertEquals(1, foundPassports.size());
        Assertions.assertEquals(666, foundPassports.get(0).getRevisionPeriod());
    }

    @Test
    public void testUpdateAviaryTypeToExistingOne(){
        testAviaryPassportCreation();
        val newType  = aviaryTypeRepository.save(new AviaryType("new type"));
        AviaryPassport passport = aviaryPassportRepository.findAll().get(0);
        passport.setAviaryType(newType);
        aviaryPassportRepository.save(passport);
        List<AviaryPassport> foundPassports = aviaryPassportRepository.findAll();
        Assertions.assertEquals(1, foundPassports.size());
        Assertions.assertEquals(newType, foundPassports.get(0).getAviaryType());
    }

    @Test
    public void testUpdateAviaryTypeToNewOne(){
        testAviaryPassportCreation();
        AviaryPassport passport = aviaryPassportRepository.findAll().get(0);
        passport.setAviaryType(new AviaryType("new type"));
        Assertions.assertThrows(Exception.class,
                () -> aviaryPassportRepository.saveAndFlush(passport));
    }
}
