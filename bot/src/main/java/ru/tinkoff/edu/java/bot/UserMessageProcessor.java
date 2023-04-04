package ru.tinkoff.edu.java.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.command.*;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.repository.UserRepository;

import java.util.List;
import java.util.Objects;

public class UserMessageProcessor {

    public static final List<Command> commands = List.of(
            new StartCommand(),
            new HelpCommand(),
            new ListCommand(),
            new TrackCommand(),
            new UntrackCommand()
    );

    public static SendMessage handle(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        User user = UserRepository.findUserByChatId(chatId);

        // тут я не понял как правильно реализовать что бы код не повторялся
        if (user == null) {
            return new StartCommand().handle(update);
        } else if (user.getBotLastActiveCommand() != null) {
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

    private static SendMessage defaultResponse(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString()); // who should get the message? the sender from which we got the message...
        sendMessage.setText("I'm sorry, but I did not understand your command");
        return sendMessage;
    }
}
