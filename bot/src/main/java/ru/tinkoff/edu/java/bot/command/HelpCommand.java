package ru.tinkoff.edu.java.bot.command;

import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.UserMessageProcessor;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("/help", "list all of the commands");
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());

        StringBuilder stringBuilder = new StringBuilder();
        for (Command command : UserMessageProcessor.commands) {
            stringBuilder.append(command.getCommand() + " - " + command.getDescription() + "\n");
        }

        String str = stringBuilder.toString();

        sendMessage.setText(str);
        return sendMessage;
    }

}
