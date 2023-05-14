package ru.tinkoff.edu.java.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dao.UserDao;

@Component
public class StartCommand extends Command {

    private UserDao userDao;
    private final ScrapperClient scrapperClient;
    public StartCommand(ScrapperClient scrapperClient) {
        super("/start", "register");
        this.scrapperClient = scrapperClient;
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        Long tgChatId = message.getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(tgChatId);

        scrapperClient.registerTgChat(tgChatId);
        sendMessage.setText("Type /help for commands");

        return sendMessage;
    }
}
