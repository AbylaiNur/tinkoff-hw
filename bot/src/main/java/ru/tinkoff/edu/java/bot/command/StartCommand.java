package ru.tinkoff.edu.java.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.dao.UserDao;

@Component
public class StartCommand extends Command {

    private UserDao userDao;
    public StartCommand(UserDao userDao) {
        super("/start", "register");
        this.userDao = userDao;
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        User user = userDao.findUserByChatId(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (user == null) {
            sendMessage.setText("You have been successfully registered!");
            userDao.addUser(chatId);
        } else {
            sendMessage.setText("Hello!");
        }

        return sendMessage;
    }
}
