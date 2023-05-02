package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {

    private final JdbcLinkRepository linkRepository;
    private final JdbcChatLinkRepository chatLinkRepository;

    @Override
    public Link add(long chatId, String url) {
        Link link = linkRepository.findByUrl(url);
        if (link == null) {
            link = new Link();
            link.setUrl(url);

            Long linkId = linkRepository.add(link);

            link.setId(linkId);
        }

        chatLinkRepository.addLinkToChat(link.getId(), chatId);
        return link;
    }

    @Override
    public Link remove(long chatId, String url) {
        Link link = linkRepository.findByUrl(url);

        if (link == null) {
            return null;
        }

        chatLinkRepository.removeLinkFromChat(link.getId(), chatId);
        return link;
    }

    @Override
    public List<Link> listAll(long chatId) {
        return chatLinkRepository.findLinksByChatId(chatId);
    }

    @Override
    public Link update(Link link) {
        linkRepository.update(link);
        return link;
    }

    @Override
    public List<Link> findAll() {
        return linkRepository.findAll();
    }
}
