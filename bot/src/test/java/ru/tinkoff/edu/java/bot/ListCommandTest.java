package ru.tinkoff.edu.java.bot;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ListCommandTest {

    private final UserRepository userRepository = mock(UserRepository.class);


//    @Before
//    public void setupTests() {
//
//        when(UserRepository.).thenReturn(new User(1000, "John"));
//        when(class2.validateObject(anyObj()).thenReturn(true);
//    }
//
//    @Test
//    public void test() {
//        Message message = new Message();
//        Chat chat = new Chat();
//        chat.setId(111l);
//        message.setChat(chat);
//        UserRepository.addUser("111");
//
//        Update update = new Update();
//
//    }
}
