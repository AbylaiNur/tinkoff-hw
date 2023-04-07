package ru.tinkoff.edu.java.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.dao.UserDao;

import java.util.List;

@Component
public class HelpCommand extends Command {

    private UserDao userDao;
    private List<Command> commands;

    public HelpCommand(UserDao userDao, List<Command> commands) {
        super("/help", "list all of the commands");
        this.commands = commands;
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());

        StringBuilder stringBuilder = new StringBuilder();
        for (Command command : commands) {
            stringBuilder.append(command.getCommand() + " - " + command.getDescription() + "\n");
        }

        String str = stringBuilder.toString();

        sendMessage.setText(str);
        return sendMessage;
    }
}
