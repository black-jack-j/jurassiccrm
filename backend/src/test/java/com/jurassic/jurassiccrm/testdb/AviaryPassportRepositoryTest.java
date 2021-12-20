package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.entity.AviaryPassport;
import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.aviary.repository.AviaryPassportRepository;
import com.jurassic.jurassiccrm.document.dto.CreateDocumentDTO;
import com.jurassic.jurassiccrm.document.entity.Document;
import com.jurassic.jurassiccrm.document.repository.DocumentRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class AviaryPassportRepositoryTest {

    @Autowired
    AviaryPassportRepository aviaryPassportRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(1)
    public void testAviaryPassportCreation(){
        Document document = new Document();
        CreateDocumentDTO createDocumentDTO = new CreateDocumentDTO();
        MockMultipartFile multipartFile = new MockMultipartFile("test", "test", "test", "TEST_AVIARY".getBytes());
        createDocumentDTO.setDocument(multipartFile);
        document.setName("test");
        document.setType("test");
        document.setContentType("text/plain");
        document.setDescription("test");
        document.setAuthor(userRepository.findByUsername("test1").orElse(null));
        document.setCreated(new Timestamp(System.currentTimeMillis()));
        document.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        try {
            document.setContent(createDocumentDTO.getDocument().getBytes());
        } catch (IOException e) {
        }
        document.setSize(document.getContent().length);
        documentRepository.save(document);
        AviaryPassport aviaryPassport = new AviaryPassport();
        aviaryPassport.setAviaryType(AviaryTypes.AVIARY_1);
        aviaryPassport.setCode(1111L);
        aviaryPassport.setDescription("testDesc");
        aviaryPassport.setBuiltDate(new Date(System.currentTimeMillis()));
        aviaryPassport.setRevisionPeriod(1);
        aviaryPassport.setStatus("Done");
        aviaryPassport.setBaseDocument(document);
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