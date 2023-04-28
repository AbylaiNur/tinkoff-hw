package ru.tinkoff.edu.java.scrapper.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.ChatLinkId;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.ChatLink;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.util.List;


@RequiredArgsConstructor
public class JpaLinkService implements LinkService {

    private final JpaChatRepository chatRepository;
    private final JpaLinkRepository linkRepository;
    private final JpaChatLinkRepository chatLinkRepository;


    @Transactional
    @Override
    public Link add(long tgChatId, String url) {

        chatRepository.findById(tgChatId).orElseThrow(
                () -> new IllegalArgumentException("Chat with id " + tgChatId + " not found")
        );

        Link link = linkRepository.findByUrl(url).orElseGet(
                () -> linkRepository.save(new Link().setUrl(url))
        );
        return link;

    }

    @Transactional
    @Override
    public Link remove(long tgChatId, String url) {
        Chat chat = chatRepository.findById(tgChatId).orElseThrow(
                () -> new IllegalArgumentException("Chat with id " + tgChatId + " not found")
        );

        Link link = linkRepository.findByUrl(url).orElseThrow(
                () -> new IllegalArgumentException("Link with url " + url + " not found")
        );

        ChatLink chatLink = chatLinkRepository.findById(
                new ChatLinkId().setChatId(chat.getId()).setLinkId(link.getId())).orElseThrow(
                () -> new IllegalArgumentException("ChatLink with chatId "
                        + chat.getId() + " and linkId " + link.getId() + " not found")
        );

        chatLinkRepository.delete(chatLink);

        return link;
    }

    @Transactional
    @Override
    public List<Link> listAll(long tgChatId) {
        Chat chat = chatRepository.findById(tgChatId).orElseThrow(
                () -> new IllegalArgumentException("Chat with id " + tgChatId + " not found")
        );
        return chatLinkRepository.findLinksByChatId(chat.getId());
    }

    @Transactional
    @Override
    public Link update(Link link) {
        return linkRepository.save(link);
    }
}
