package ru.tinkoff.edu.java.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.client.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.component.ChatStates;

import java.net.URI;
import java.util.List;


@Component
public class UntrackCommand extends Command {

    private final ScrapperClient scrapperClient;
    private final ChatStates chatStates;
    public UntrackCommand(ChatStates chatStates,
                          ScrapperClient scrapperClient) {
        super("/untrack", "remove the tracked link by its index");
        this.chatStates = chatStates;
        this.scrapperClient = scrapperClient;
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String state = chatStates.getState(chatId);

        List<URI> links = scrapperClient.listLinks(chatId)
            .links()
            .stream()
            .map(link -> link.url())
            .toList();


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (state == null && links.isEmpty()) {
            sendMessage.setText(
                "The list is empty. There is nothing to untrack."
            );
        } else if (state == null) {

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < links.size(); i++) {
                stringBuilder.append((i + 1) + ". " + links.get(i) + "\n");
            }
            String str = stringBuilder.toString();
            sendMessage.setText("Write the number of the link, "
                + "that you would like to untrack.\n" + str);

            chatStates.setState(chatId, "waitingLinkIndex");
            chatStates.setLastActiveCommand(chatId, this.getCommand());
        } else if (isNumeric(message.getText())
                && 0 < Integer.parseInt(message.getText())
                && Integer.parseInt(message.getText()) <= links.size()) {

            RemoveLinkRequest removeLinkRequest =
                new RemoveLinkRequest(
                    links.get(Integer.parseInt(message.getText()) - 1));
            scrapperClient.deleteLinks(chatId, removeLinkRequest);

            sendMessage.setText("Successfully removed your link");
            chatStates.setState(chatId, null);
            chatStates.setLastActiveCommand(chatId, null);
        } else {
            sendMessage.setText(
                "This kind of index does not exist. \nPlease type again."
            );
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
