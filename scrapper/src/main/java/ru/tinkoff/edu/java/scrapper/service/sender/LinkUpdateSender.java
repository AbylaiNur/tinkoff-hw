package ru.tinkoff.edu.java.scrapper.service.sender;

import java.util.List;

public interface LinkUpdateSender {
    void send(Long id, String url, String description, List<Long> tgChatIds);
}
