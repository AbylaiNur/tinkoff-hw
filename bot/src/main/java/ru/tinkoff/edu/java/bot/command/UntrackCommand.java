package ru.tinkoff.edu.java.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.model.User;
import ru.tinkoff.edu.java.bot.dao.UserDao;

import java.util.List;

@Component
public class UntrackCommand extends Command {

    private UserDao userDao;
    public UntrackCommand(UserDao userDao) {
        super("/untrack", "removes the link by its index");
        this.userDao = userDao;
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        User user = userDao.findUserByChatId(chatId);
        String state = user.getBotState();
        List<String> links = user.getLinks();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());

        if (state == null && links.isEmpty()) {
            sendMessage.setText("The list is empty. There is nothing to untrack.");
        } else if (state == null) {

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < links.size(); i++) {
                stringBuilder.append(String.valueOf(i + 1) + ". " + links.get(i) + "\n");
            }
            String str = stringBuilder.toString();
            sendMessage.setText("Write the number of the link, that you would like to untrack.\n" + str);

            userDao.updateBotState(chatId, "waitingLinkIndex");
            userDao.updateBotLastActiveCommand(chatId, this.getCommand());
        } else if (isNumeric(message.getText())
                && 0 < Integer.parseInt(message.getText())
                && Integer.parseInt(message.getText()) <= links.size()) {
            userDao.deleteLinkByIndex(chatId, Integer.parseInt(message.getText()) - 1);

            sendMessage.setText("Successfully removed your link");
            userDao.updateBotState(chatId, null);
            userDao.updateBotLastActiveCommand(chatId, null);
        } else {
            sendMessage.setText("This kind of index does not exist. \nPlease type again.");
        }

        return sendMessage;
    }

    private Boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer number = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
