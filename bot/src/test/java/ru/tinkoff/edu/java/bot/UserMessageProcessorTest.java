package ru.tinkoff.edu.java.bot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.command.Command;
import ru.tinkoff.edu.java.bot.dao.UserDao;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.service.UserMessageProcessor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserMessageProcessorTest {

    @Mock
    private UserDao userDaoMock;

    @Spy
    private ArrayList<Command> commandsMock;

    @InjectMocks
    private UserMessageProcessor userMessageProcessorMock;

    @Test
    public void handleTest_withUnknownCommandMessage() {
        Chat chat = new Chat();
        chat.setId(0l);

        Message message = new Message();
        message.setChat(chat);
        message.setText("fkdsljfflaj;fjsja;lfkdjlsjlfkjs");

        Update update = new Update();
        update.setMessage(message);

        when(userDaoMock.findUserByChatId(any())).thenReturn(new User(0l));

        assertEquals("I'm sorry, but I don't understand your command",
                userMessageProcessorMock.handle(update).getText());
    }
}
