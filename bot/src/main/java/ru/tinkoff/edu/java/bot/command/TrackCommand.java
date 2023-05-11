package ru.tinkoff.edu.java.bot.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.client.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.component.ChatStates;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.dao.UserDao;

import java.net.URI;

// In process
@Component
public class TrackCommand extends Command {

    private final ChatStates chatStates;
    private final ScrapperClient scrapperClient;

    public TrackCommand(ChatStates chatStates, ScrapperClient scrapperClient) {
        super("/track", "track provided link");
        this.chatStates = chatStates;
        this.scrapperClient = scrapperClient;
    }

    @Override
    public SendMessage handle(Update update) {


        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String state = chatStates.getState(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        if (state == null) {
            sendMessage.setText("Write your link");
            chatStates.setState(chatId, "waitingLink");
//            userDao.updateBotState(chatId, "waitingLink");
            chatStates.setLastActiveCommand(chatId, this.getCommand());
//            userDao.updateBotLastActiveCommand(chatId, this.getCommand());
        } else {
//            userDao.addLink(chatId, message.getText());
            AddLinkRequest addLinkRequest = null;
            addLinkRequest = new AddLinkRequest(URI.create(message.getText()));
            scrapperClient.addLink(chatId, addLinkRequest);
            sendMessage.setText("Successfully added your link");
//            userDao.updateBotState(chatId, null);
            chatStates.setState(chatId, null);
//            userDao.updateBotLastActiveCommand(chatId, null);
            chatStates.setLastActiveCommand(chatId, null);
        }

        return sendMessage;
    }
}


