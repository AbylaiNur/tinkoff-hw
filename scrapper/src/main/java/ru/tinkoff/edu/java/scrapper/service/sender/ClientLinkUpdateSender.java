package ru.tinkoff.edu.java.scrapper.service.sender;

import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.client.TgBotClient;

import java.util.List;

@RequiredArgsConstructor
public class ClientLinkUpdateSender implements LinkUpdateSender {

    private final TgBotClient tgBotClient;

    @Override
    public void send(Long id, String url, String description, List<Long> tgChatIds) {
        tgBotClient.updates(id, url, description, tgChatIds);
    }
}
