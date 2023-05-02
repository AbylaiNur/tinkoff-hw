package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.MyBot;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;

@Service
@RequiredArgsConstructor
public class LinkUpdateProcessor {
    private final MyBot myBot;
    public void process(LinkUpdateRequest request) {
        for (Long tgChatId : request.tgChatIds()) {
             myBot.notifyUser(tgChatId, request.url(), request.description());
        }
    }
}
