package ru.tinkoff.edu.java.scrapper.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ContextConfiguration(classes = ScrapperApplication.class)
public class JpaLinkTest extends IntegrationEnvironment {
    @Autowired
    private JpaLinkRepository linkRepository;

    @AfterEach
    public void resetTables() {
        linkRepository.deleteAll();
    }

    @Transactional
    @Rollback
    @Test
    public void findByUrlTest() {
        Link link = new Link();
        link.setUrl("www");

        link = linkRepository.save(link);

        Link linkDB = linkRepository.findByUrl(link.getUrl()).orElse(null);
        assertEquals(link, linkDB);
    }
}
