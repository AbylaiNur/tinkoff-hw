package ru.tinkoff.edu.java.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.client.dto.response.LinkResponse;
import ru.tinkoff.edu.java.bot.client.dto.response.ListLinksResponse;

import java.util.List;

@Component
public class ListCommand extends Command {

    private final ScrapperClient scrapperClient;

    public ListCommand(ScrapperClient scrapperClient) {
        super("/list", "list all of the tracked links");
        this.scrapperClient = scrapperClient;
    }


    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();

        Long tgChatId = message.getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(tgChatId);

        ListLinksResponse listLinksResponse =
            scrapperClient.listLinks(tgChatId);

        List<LinkResponse> links = listLinksResponse.links();

        if (links.isEmpty()) {
            sendMessage.setText("Your tracking list is empty.\n"
                + "Please add some links with /track command.");
            return sendMessage;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < links.size(); i++) {
            stringBuilder.append((i + 1) + ". "
                + links.get(i).url().toString() + "\n");
        }

        String str = stringBuilder.toString();

        sendMessage.setText(str);
        return sendMessage;
    }
}
