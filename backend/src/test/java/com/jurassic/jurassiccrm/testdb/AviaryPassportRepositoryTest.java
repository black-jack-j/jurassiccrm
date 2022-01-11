package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.entity.AviaryPassport;
import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.aviary.repository.AviaryPassportRepository;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class AviaryPassportRepositoryTest {

    @Autowired
    AviaryPassportRepository aviaryPassportRepository;

    @Autowired
    UserRepository userRepository;

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
        AviaryPassport aviaryPassport = new AviaryPassport();
        aviaryPassport.setName("test");
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
        assert aviaryPassport.getType() == DocumentType.AVIARY_PASSPORT;
        assert foundAviariesPassport.contains(aviaryPassport);
    }

    @Test
    public void testAviaryPassportUpdate(){
        testAviaryPassportCreation();
        AviaryPassport passport = aviaryPassportRepository.findAll().get(0);
        passport.setName("Updated");
        passport.setRevisionPeriod(666);
        aviaryPassportRepository.save(passport);
        List<AviaryPassport> foundPassports = aviaryPassportRepository.findAll();
        assert foundPassports.size() == 1;
        assert foundPassports.get(0).getName().equals("Updated");
        assert foundPassports.get(0).getRevisionPeriod() == 666;
    }
}
