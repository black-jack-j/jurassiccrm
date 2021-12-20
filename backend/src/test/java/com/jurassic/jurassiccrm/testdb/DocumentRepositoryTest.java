package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
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
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    private String name = "file";
    private String filename = "test.txt";
    private String contentType = "text/plain";
    private String content = "Spring Framework Test File For JurassicCRM";

    private Document createDocument(){
        CreateDocumentDTO createDocumentDTO = new CreateDocumentDTO();
        MockMultipartFile multipartFile = new MockMultipartFile(name, filename, contentType, content.getBytes());
        createDocumentDTO.setDocument(multipartFile);
        Document document = new Document();
        document.setName("test-document");
        document.setType("test-type");
        document.setContentType("text/plain");
        document.setDescription("test-description");
        document.setAuthor(userRepository.findByUsername("admin").orElse(null));
        document.setCreated(new Timestamp(System.currentTimeMillis()));
        document.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        try {
            document.setContent(createDocumentDTO.getDocument().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.setSize(document.getContent().length);
        return document;
    }

    @Transactional
    @Rollback(false)
    @Test
    @Order(1)
    public void testDocumentUpload(){
        Document document = createDocument();
        documentRepository.save(document);
        List<Document> documentsFromRepository = documentRepository.findAll();
        boolean foundTestDocument = false;
        for (Document document_ : documentsFromRepository){
            if (document_.equals(document)){
                foundTestDocument = true;
                break;
            }
        }
        assert foundTestDocument;
    }

    @Transactional
    @Rollback(false)
    @Test
    @Order(2)
    public void testDocumentDeletion(){
        List<Document> documentsFromRepository = documentRepository.findAll();
        byte[] contentBytes = content.getBytes();
        Document foundDoc = new Document();
        for (Document doc : documentsFromRepository){
            byte[] docBytes = doc.getContent();
            if (Arrays.equals(docBytes, contentBytes)){
                foundDoc = doc;
                documentRepository.delete(doc);
                break;
            }
        }
        assert !(documentRepository.findAll().contains(foundDoc));
    }
}