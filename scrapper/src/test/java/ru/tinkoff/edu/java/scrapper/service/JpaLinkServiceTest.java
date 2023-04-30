package ru.tinkoff.edu.java.scrapper.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.tinkoff.edu.java.scrapper.configuration.JpaServiceTestConfig;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.ChatLink;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkService;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(JpaServiceTestConfig.class)
public class JpaLinkServiceTest extends IntegrationEnvironment {

    @Autowired
    @Qualifier("jpa-link-service-test")
    private JpaLinkService linkService;


    @Autowired
    private JpaLinkRepository linkRepository;

    @Autowired
    private JpaChatLinkRepository chatLinkRepository;

    @Autowired
    private JpaChatRepository chatRepository;

    @BeforeEach
    void setUp() {
        chatRepository.save(new Chat().setId(1l));
    }

    @AfterEach
    void after() {
        chatLinkRepository.deleteAll();
        linkRepository.deleteAll();
        chatRepository.deleteAll();
    }

    @Test
    void addTest() {
        Link linkResponse = linkService.add(1l, "www");
        Link linkFromLinkRepo = linkRepository.findAll().get(0);
        Link linkFromChatLinkRepo = chatLinkRepository.findAllByChatId(1l).get(0).getLink();

        assertEquals(linkResponse, linkFromLinkRepo);
        assertEquals(linkResponse, linkFromChatLinkRepo);
    }

    @Test
    void removeTest() {
        Link link = linkRepository.save(new Link().setUrl("www"));
        ChatLink chatLink = chatLinkRepository.save(
                new ChatLink().setChatId(1l).setLinkId(link.getId())
        );

        assertEquals(link, linkRepository.findAll().get(0));

        ChatLink chatLinkFromRepo = chatLinkRepository.findAll().get(0);
        assertEquals(chatLink.getLinkId(), chatLinkFromRepo.getLinkId());
        assertEquals(chatLink.getChatId(), chatLinkFromRepo.getChatId());

        linkService.remove(1l, "www");

        assertTrue(chatLinkRepository.findAll().isEmpty());
    }

    @Test
    void listAllTest() {
        Link link1 = linkRepository.save(new Link().setUrl("www1"));
        Link link2 = linkRepository.save(new Link().setUrl("www2"));
        Link link3 = linkRepository.save(new Link().setUrl("www3"));

        Set<Link> links = Set.of(link1, link2, link3);

        for (Link link : links) {
            chatLinkRepository.save(new ChatLink().setChatId(1l).setLinkId(link.getId()));
        }

        List<Link> linksFromService = linkService.listAll(1l);
        Set<Link> linksFromServiceSet = new HashSet<>(linksFromService);

        assertEquals(links, linksFromServiceSet);
    }

    @Test
    void updateTest() {
        Link link = linkRepository.save(new Link().setUrl("www"));
        assertEquals(link, linkRepository.findAll().get(0));

        link.setUrl("new.www");
        linkService.update(link);
        assertEquals(link, linkRepository.findAll().get(0));
    }
}
