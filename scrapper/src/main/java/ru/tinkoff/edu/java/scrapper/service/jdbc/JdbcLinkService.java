package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {


    private final JdbcLinkRepository linkRepository;

    @Override
    public Link add(long chatId, URI url) {
        Link link = linkRepository.findByUrl(url);
        if (link == null) {
            link = new Link();
            link.setUrl(url);

            Long linkId = linkRepository.add(link);

            link.setId(linkId);
        }

        linkRepository.addLinkToChat(link.getId(), chatId);
        return link;
    }

    @Override
    public Link remove(long chatId, URI url) {
        Link link = linkRepository.findByUrl(url);

        if (link == null) {
            return null;
        }

        linkRepository.removeLinkFromChat(link.getId(), chatId);
        return link;
    }

    @Override
    public List<Link> listAll(long chatId) {
        return linkRepository.findAllByChatId(chatId);
    }
}

// Q: difference between Collection and List
//