package ru.tinkoff.edu.java.bot;

import org.junit.jupiter.api.BeforeEach;
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
import ru.tinkoff.edu.java.bot.command.ListCommand;
import ru.tinkoff.edu.java.bot.dao.UserDao;
import ru.tinkoff.edu.java.bot.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserMessageProcessorTest {

    @Mock
    private UserDao userDaoMock;

    @Spy @InjectMocks
    private ListCommand listCommandMock;

    @Spy
    private ArrayList<Command> commandsMock;

    @InjectMocks
    private UserMessageProcessor userMessageProcessorMock;

    @BeforeEach
    public void setUp() {
        commandsMock.add(listCommandMock);
    }

    @Test
    public void userMessageProcessor_listCommand() {
        User user = new User(0l);
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();

        user.setLinks(List.of("https://www.youtube.com"));
        chat.setId(0l);
        message.setChat(chat);
        message.setText("/list");
        update.setMessage(message);


        when(userDaoMock.findUserByChatId(any())).thenReturn(user);
        assertEquals("1. https://www.youtube.com\n",
                userMessageProcessorMock.handle(update).getText());
    }

    @Test
    public void userMessageProcessor_listCommand_withEmptyList() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();

        chat.setId(0l);
        message.setChat(chat);
        message.setText("/list");
        update.setMessage(message);

        when(userDaoMock.findUserByChatId(any())).thenReturn(new User(0l));
        assertEquals("Your tracking list is empty.\nPlease add some links with /track command.",
                userMessageProcessorMock.handle(update).getText());
    }


    @Test
    public void userMessageProcessor_withUnknownCommandMessage() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();

        chat.setId(0l);
        message.setChat(chat);
        message.setText("fkdsljfflaj;fjsja;lfkdjlsjlfkjs");
        update.setMessage(message);

        when(userDaoMock.findUserByChatId(any())).thenReturn(new User(0l));
        assertEquals("I'm sorry, but I did not understand your command",
                userMessageProcessorMock.handle(update).getText());
    }
}
