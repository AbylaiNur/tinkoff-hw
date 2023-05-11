package ru.tinkoff.edu.java.bot.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.command.*;
import ru.tinkoff.edu.java.bot.component.ChatStates;

import java.util.List;
import java.util.Objects;

@Getter
@Service
@AllArgsConstructor
public class UserMessageProcessor {

//    private UserDao userDao;
    private List<Command> commands;
    private ChatStates chatStates;
    public SendMessage handle(Update update) {
        Long chatId = update.getMessage().getChatId();

        String lastActiveCommand = chatStates.getLastActiveCommand(chatId);

        if (lastActiveCommand != null) {
            for (Command command : commands) {
                if (Objects.equals(command.getCommand(), lastActiveCommand)) {
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
