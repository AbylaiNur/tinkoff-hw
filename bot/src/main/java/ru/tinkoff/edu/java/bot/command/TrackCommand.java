package ru.tinkoff.edu.java.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.repository.UserRepository;

public class TrackCommand extends Command {
    public TrackCommand() {
        super("/track", "tracks provided link");
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        User user = UserRepository.findUserByChatId(chatId);
        String state = user.getBotState();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());

        if (state == null) {
            sendMessage.setText("Write your link");
            UserRepository.updateBotState(chatId, "waitingLink");
            UserRepository.updateBotLastActiveCommand(chatId, this.getCommand());
        } else {
            UserRepository.addLink(chatId, message.getText());
            sendMessage.setText("Successfully added your link");
            UserRepository.updateBotState(chatId, null);
            UserRepository.updateBotLastActiveCommand(chatId, null);
        }

        return sendMessage;
    }
}
