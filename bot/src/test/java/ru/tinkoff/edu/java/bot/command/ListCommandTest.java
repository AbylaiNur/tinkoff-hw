package ru.tinkoff.edu.java.bot.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.dao.UserDao;
import ru.tinkoff.edu.java.bot.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListCommandTest {

    @Mock
    private UserDao userDaoMock;

    @InjectMocks
    private ListCommand listCommand;

    @Test
    public void handleTest_withEmptyList() {
        Chat chat = new Chat();
        chat.setId(0l);

        Message message = new Message();
        message.setChat(chat);
        message.setText("/list");

        Update update = new Update();
        update.setMessage(message);

        when(userDaoMock.findUserByChatId(any())).thenReturn(new User(0l));

        assertEquals(
                "Your tracking list is empty.\nPlease add some links with /track command.",
                listCommand.handle(update).getText());
    }

    @Test
    public void handleTest() {
        Chat chat = new Chat();
        chat.setId(0l);

        Message message = new Message();
        message.setChat(chat);
        message.setText("/list");

        Update update = new Update();
        update.setMessage(message);

        User user = new User(0l);
        user.setLinks(List.of("https://www.youtube.com"));

        when(userDaoMock.findUserByChatId(any())).thenReturn(user);

        assertEquals(
                "1. https://www.youtube.com\n",
                listCommand.handle(update).getText());
    }
}
