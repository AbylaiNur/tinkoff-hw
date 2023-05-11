package ru.tinkoff.edu.java.scrapper.service;

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
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaChatService;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(JpaServiceTestConfig.class)
public class JpaChatServiceTest extends IntegrationEnvironment {

    @Autowired
    @Qualifier("jpa-chat-service-test")
    private JpaChatService chatService;

    @Autowired
    private JpaChatRepository chatRepository;

    @Autowired
    private JpaLinkRepository linkRepository;

    @Autowired
    private JpaChatLinkRepository chatLinkRepository;

    @Test
    void registerTest() {
        chatService.register(1l);
        assertEquals(1l, chatRepository.findById(1l).get().getId());
    }

    @Test
    void unregisterTest() {
        chatRepository.save(new Chat().setId(1l));

        chatService.unregister(1l);

        assertEquals(0, chatRepository.count());
    }

    @Test
    void findAllByLinkTest() {
        Link link = linkRepository.save(new Link().setUrl("www"));

        Chat chat1 = chatRepository.save(new Chat().setId(1l));
        Chat chat2 = chatRepository.save(new Chat().setId(2l));
        Chat chat3 = chatRepository.save(new Chat().setId(3l));

        Set<Chat> chatSet = Set.of(chat1, chat2, chat3);

        for (Chat chat : chatSet) {
            ChatLink chatLink = chatLinkRepository.save(new ChatLink()
                    .setChatId(chat.getId())
                    .setLinkId(link.getId())
            );
            chatLinkRepository.save(chatLink);
        }

        Set<Chat> chatSetFromService = new HashSet<>(chatService.findAllByLink(link.getUrl()));

        assertEquals(chatSet, chatSetFromService);
    }


}
