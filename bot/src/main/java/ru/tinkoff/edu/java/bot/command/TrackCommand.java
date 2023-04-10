package ru.tinkoff.edu.java.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.dao.UserDao;

@Component
public class TrackCommand extends Command {

    private UserDao userDao;

    public TrackCommand(UserDao userDao) {
        super("/track", "track provided link");
        this.userDao = userDao;
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        User user = userDao.findUserByChatId(chatId);
        String state = user.getBotState();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        if (state == null) {
            sendMessage.setText("Write your link");
            userDao.updateBotState(chatId, "waitingLink");
            userDao.updateBotLastActiveCommand(chatId, this.getCommand());
        } else {
            userDao.addLink(chatId, message.getText());
            sendMessage.setText("Successfully added your link");
            userDao.updateBotState(chatId, null);
            userDao.updateBotLastActiveCommand(chatId, null);
        }

        return sendMessage;
    }
}
