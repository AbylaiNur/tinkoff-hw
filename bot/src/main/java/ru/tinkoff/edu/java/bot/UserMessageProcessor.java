package ru.tinkoff.edu.java.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.command.*;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.dao.UserDao;

import java.util.List;
import java.util.Objects;

@Getter
@Component
@AllArgsConstructor
public class UserMessageProcessor {

    private UserDao userDao;
    private List<Command> commands;

    public SendMessage handle(Update update) {
        Long chatId = update.getMessage().getChatId();
        User user = userDao.findUserByChatId(chatId);

        if (user != null && user.getBotLastActiveCommand() != null) {
            for (Command command : commands) {
                if (Objects.equals(command.getCommand(), user.getBotLastActiveCommand())) {
                    return command.handle(update);
                }
            }
        } else {
            String textMessage = update.getMessage().getText();
            for (Command command : commands) {
                if (Objects.equals(command.getCommand(), textMessage)) {
                    return command.handle(update);
                }
            }
        }

        return defaultResponse(update);
    }

    private SendMessage defaultResponse(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("I'm sorry, but I don't understand your command");
        return sendMessage;
    }
}
