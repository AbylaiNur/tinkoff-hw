package ru.tinkoff.edu.java.scrapper.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.ChatLink;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class JpaChatLinkTest extends IntegrationEnvironment {


    @Autowired
    private JpaChatLinkRepository chatLinkRepository;

    @Autowired
    private JpaLinkRepository linkRepository;

    @Autowired
    private JpaChatRepository chatRepository;

    @AfterEach
    public void resetTables() {
        chatLinkRepository.deleteAll();
        linkRepository.deleteAll();
        chatRepository.deleteAll();
    }

    @Transactional
    @Rollback
    @Test
    public void findLinksByChatIdTest() {
        Link link1 = linkRepository.save(new Link().setUrl("www1"));
        Link link2 = linkRepository.save(new Link().setUrl("www2"));

        Chat chat1 = chatRepository.save(new Chat().setId(1l));
        Chat chat2 = chatRepository.save(new Chat().setId(2l));

        ChatLink chatLink1 = new ChatLink().setLinkId(link1.getId()).setChatId(chat1.getId());
        ChatLink chatLink2 = new ChatLink().setLinkId(link2.getId()).setChatId(chat2.getId());

        chatLinkRepository.save(chatLink1);
        chatLinkRepository.save(chatLink2);

        Set<Link> chatLinks = Set.of(link1);
        Set<Link> chatLinksDB = new HashSet<>(chatLinkRepository.findLinksByChatId(chat1.getId()));

        assertEquals(chatLinks, chatLinksDB);
    }

    @Transactional
    @Rollback
    @Test
    public void findChatsByLinkIdTest() {
        Link link1 = linkRepository.save(new Link().setUrl("www1"));
        Link link2 = linkRepository.save(new Link().setUrl("www2"));

        Chat chat1 = chatRepository.save(new Chat().setId(1l));
        Chat chat2 = chatRepository.save(new Chat().setId(2l));

        ChatLink chatLink1 = new ChatLink().setLinkId(link1.getId()).setChatId(chat1.getId());
        ChatLink chatLink2 = new ChatLink().setLinkId(link2.getId()).setChatId(chat2.getId());

        chatLinkRepository.save(chatLink1);
        chatLinkRepository.save(chatLink2);

        Set<Chat> linkChats = Set.of(chat1);
        Set<Chat> linkChatsDB = new HashSet<>(chatLinkRepository.findChatsByLinkId(link1.getId()));

        assertEquals(linkChats, linkChatsDB);
    }

    @Transactional
    @Rollback
    @Test
    void findByChatIdAndLinkIdTest() {
        Link link1 = linkRepository.save(new Link().setUrl("www1"));
        Link link2 = linkRepository.save(new Link().setUrl("www2"));

        Chat chat1 = chatRepository.save(new Chat().setId(1l));
        Chat chat2 = chatRepository.save(new Chat().setId(2l));

        ChatLink chatLink1 = new ChatLink().setLinkId(link1.getId()).setChatId(chat1.getId());
        ChatLink chatLink2 = new ChatLink().setLinkId(link2.getId()).setChatId(chat2.getId());

        chatLinkRepository.save(chatLink1);
        chatLinkRepository.save(chatLink2);


        assertEquals(chatLink1, chatLinkRepository.findByChatIdAndLinkId(chat1.getId(), link1.getId())
                .orElse(null));

        assertEquals(chatLink2, chatLinkRepository.findByChatIdAndLinkId(chat2.getId(), link2.getId())
                .orElse(null));
    }

    @Transactional
    @Rollback
    @Test
    void deleteByChatIdAndLinkId() {
        Link link1 = linkRepository.save(new Link().setUrl("www1"));

        Chat chat1 = chatRepository.save(new Chat().setId(1l));

        ChatLink chatLink1 = new ChatLink().setLinkId(link1.getId()).setChatId(chat1.getId());

        chatLinkRepository.save(chatLink1);

        assertEquals(chatLink1, chatLinkRepository.findByChatIdAndLinkId(chat1.getId(), link1.getId())
                .orElse(null));

        chatLinkRepository.deleteByChatIdAndLinkId(chat1.getId(), link1.getId());

        assertNull(chatLinkRepository.findByChatIdAndLinkId(chat1.getId(), link1.getId())
                .orElse(null));
    }
}
