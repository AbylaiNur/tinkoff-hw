package ru.tinkoff.edu.java.scrapper.repository;

import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;

import java.util.List;

public interface ChatLinkRepository {
    List<Link> findLinksByChatId(Long chatId);
    List<Chat> findChatsByLinkId(Long linkId);
    boolean addLinkToChat(Long linkId, Long chatId);
    boolean removeLinkFromChat(Long linkId, Long chatId);
}
