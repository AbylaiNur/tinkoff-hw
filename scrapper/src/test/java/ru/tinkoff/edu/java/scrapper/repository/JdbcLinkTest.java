package ru.tinkoff.edu.java.scrapper.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JdbcLinkTest extends IntegrationEnvironment {


    @Autowired
    private JdbcLinkRepository linkRepository;

    @Autowired
    private JdbcChatRepository chatRepository;

    @AfterEach
    public void resetTables() {
        linkRepository.removeAll();
    }

    @Test
    void addTest() {
        Link link = new Link();
        link.setUrl("www");
        Long linkId = linkRepository.add(link);
        link.setId(linkId);

        Link link1 = linkRepository.findAll().get(0);

        assertEquals(link, link1);
    }

    @Test
    void removeTest() {
        Link link = new Link();
        link.setUrl("www1");
        Long linkId = linkRepository.add(link);

        linkRepository.remove(linkId);
        assertTrue(linkRepository.findAll().isEmpty());
    }

    @Test
    void findAll() {
        Link link1 = new Link();
        link1.setUrl("w1");
        Long linkId1 = linkRepository.add(link1);
        link1.setId(linkId1);

        Link link2 = new Link();
        link2.setUrl("w2");
        Long linkId2 = linkRepository.add(link2);
        link2.setId(linkId2);

        Set<Link> links = Set.of(link1, link2);
        Set<Link> linksDB = new HashSet<>(linkRepository.findAll());

        assertEquals(links, linksDB);
    }

    @Test
    void findLinkByUrlTest_linkIsInDB() {
        Link link = new Link();
        link.setUrl("https://stackoverflow.com/");

        Long id = linkRepository.add(link);
        link.setId(id);

        Link link1 = null;

        try {
            link1 = linkRepository.findByUrl(new URI(link.getUrl()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        assertEquals(link, link1);
    }

    @Test
    void findLinkByUrlTest_linkIsNotInDB() {
        Link link = new Link();
        link.setUrl("https://stackoverflow.com/");

        Long id = linkRepository.add(link);
        link.setId(id);

        Link link1 = null;

        try {
            link1 = linkRepository.findByUrl(new URI("https://github.com/"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        assertNotEquals(link, link1);
    }

    @Test
    void addLinkToChat_and_FindAllByChatIdTest() {
        Chat chat = new Chat();
        chat.setId(1l);
        chatRepository.add(chat.getId());

        Link link = new Link();
        link.setUrl("https://github.com/");
        Long linkId = linkRepository.add(link);
        link.setId(linkId);

        linkRepository.addLinkToChat(link.getId(), chat.getId());

        Link link1 = linkRepository.findAllByChatId(chat.getId()).get(0);

        assertEquals(link, link1);
    }
}