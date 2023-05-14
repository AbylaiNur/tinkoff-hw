package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

import java.util.List;

@RequiredArgsConstructor
public class JdbcChatService implements ChatService {


    private final JdbcChatRepository chatRepository;
    private final JdbcLinkRepository linkRepository;
    private final JdbcChatLinkRepository chatLinkRepository;

    @Override
    public void register(long tgChatId) {
        chatRepository.add(tgChatId);
    }

    @Override
    public void unregister(long tgChatId) {
        chatRepository.remove(tgChatId);
    }

    @Override
    public List<Chat> findAllByLink(String url) {
        Link link = linkRepository.findByUrl(url);
        return chatLinkRepository.findChatsByLinkId(link.getId());
    }
}
