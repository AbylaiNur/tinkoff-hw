package ru.tinkoff.edu.java.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.repository.UserRepository;

public class StartCommand extends Command {
    public StartCommand() {
        super("/start", "registers user");
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        User user = UserRepository.findUserByChatId(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (user == null) {
            sendMessage.setText("You have been successfully registered");
            UserRepository.addUser(chatId);
        } else {
            sendMessage.setText("Hello!");
        }

        return sendMessage;
    }
}
