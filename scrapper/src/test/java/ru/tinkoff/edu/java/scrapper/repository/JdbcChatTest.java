package ru.tinkoff.edu.java.scrapper.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JdbcChatTest extends IntegrationEnvironment {


    @Autowired
    private JdbcChatRepository chatRepository;

    @AfterEach
    public void resetTables() {
        chatRepository.removeAll();
    }

    @Test
    void addTest() {
        Long tgChatId = 123l;
        Chat chat = new Chat();
        chat.setId(tgChatId);

        chatRepository.add(tgChatId);

        Chat chat1 = chatRepository.findAll().get(0);

        assertEquals(chat, chat1);
    }

    @Test
    void removeTest() {
        Long chatId = 1l;
        chatRepository.add(chatId);
        chatRepository.remove(chatId);

        assertTrue(chatRepository.findAll().isEmpty());
    }

    @Test
    void findAll() {
        Chat chat1 = new Chat();
        chat1.setId(1l);
        chatRepository.add(1l);

        Chat chat2 = new Chat();
        chat2.setId(2l);
        chatRepository.add(2l);

        Set<Chat> chats = Set.of(chat1, chat2);
        Set<Chat> chatsDB = new HashSet<>(chatRepository.findAll());

        assertEquals(chats, chatsDB);
    }
}
