package ru.tinkoff.edu.java.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.repository.UserRepository;

public class UntrackCommand extends Command {
    public UntrackCommand() {
        super("/untrack", "removes the link by its index");
    }

    // Not finished yet
    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        User user = UserRepository.findUserByChatId(chatId);
        String state = user.getBotState();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());

        if (state == null && user.getLinks().isEmpty()) {
            sendMessage.setText("The list is empty. There is nothing to untrack.");
        } else if (state == null) {
            sendMessage.setText("Write the number of the link, that you would like to untrack.");
            UserRepository.updateBotState(chatId, "waitingLinkIndex");
            UserRepository.updateBotLastActiveCommand(chatId, this.getCommand());
        } else {
            // here  should be removeLink()
            sendMessage.setText("Successfully removed your link");
            UserRepository.updateBotState(chatId, null);
            UserRepository.updateBotLastActiveCommand(chatId, null);
        }

        return sendMessage;
    }
}
