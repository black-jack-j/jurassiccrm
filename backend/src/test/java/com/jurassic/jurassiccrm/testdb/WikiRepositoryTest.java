package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.wiki.entity.Wiki;
import com.jurassic.jurassiccrm.wiki.repository.WikiRepository;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class WikiRepositoryTest {
    @Autowired
    private WikiRepository wikiRepository;

    private final String title = "Test Page";

    @Transactional
    @Rollback(false)
    @Test
    @Order(1)
    public void testWikiPageCreation(){
        Wiki wiki = new Wiki();
        wiki.setTitle(title);
        wiki.setText("Test Wiki Page text");
        try {
            URL url = new URL("http://andrey-eltsov.ru/wp-content/uploads/2019/04/XxXx-XX123-lJuO_4gfqQ-s_4hnSuJ_gdt43sdYH-d_G-F-Y-k_341-afR-Тираннозавр-фотоголубой2.jpg");
            InputStream is = url.openStream();
            byte[] bytes = IOUtils.toByteArray(is);
            wiki.setImage(bytes);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wikiRepository.saveAndFlush(wiki);
        assert wikiRepository.findByTitle(title).equals(wiki);
    }

    @Transactional
    @Rollback(false)
    @Test
    @Order(2)
    public void testWikiPageUpdate(){
        String newTestText = "New Test Text";
        assert wikiRepository.findByTitle(title) != null;
        Wiki wiki = wikiRepository.findByTitle(title);
        wiki.setText(newTestText);
        wikiRepository.save(wiki);
        Wiki updatedWiki = wikiRepository.findByTitle(title);
        assert Objects.equals(updatedWiki.getText(), newTestText);

    }


    @Transactional
    @Rollback(false)
    @Test
    @Order(3)
    public void testWikiPageDeletion(){
        assert wikiRepository.findByTitle(title) != null;
        Wiki wiki = wikiRepository.findByTitle(title);
        wikiRepository.delete(wiki);
        assert wikiRepository.findByTitle(title) == null;

    }
}
