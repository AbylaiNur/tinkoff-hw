package ru.tinkoff.edu.java.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.dao.UserDao;

import java.util.List;

@Component
public class ListCommand extends Command {

    private UserDao userDao;

    public ListCommand(UserDao userDao) {
        super("/list", "lists all of the tracked links");
        this.userDao = userDao;
    }


    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());

        User user = userDao.findUserByChatId(message.getChatId());

        List<String> links = user.getLinks();

        if (links.isEmpty()) {
            sendMessage.setText("Your tracking list is empty.\nPlease add some links with /track command.");
            return sendMessage;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < links.size(); i++) {
            stringBuilder.append(String.valueOf(i + 1) + ". " + links.get(i) + "\n");
        }

        String str = stringBuilder.toString();

        sendMessage.setText(str);
        return sendMessage;
    }
}
