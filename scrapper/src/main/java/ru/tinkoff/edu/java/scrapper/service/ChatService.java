package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.model.Chat;

import java.util.List;

public interface ChatService {
    void register(long tgChatId);
    void unregister(long tgChatId);
    List<Chat> findAllByLink(String url);
}
