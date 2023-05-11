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

        ChatLink chatLink = chatLinkRepository.findById(
                new ChatLinkId()
                        .setChatId(tgChatId)
                        .setLinkId(link.getId())).orElseGet(
                () -> chatLinkRepository.save(new ChatLink().setChatId(tgChatId).setLinkId(link.getId()))
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
        return chatLinkRepository.findAllByChatId(chat.getId()).stream().map(chatLink -> chatLink.getLink()).toList();
    }

    @Transactional
    @Override
    public Link update(Link link) {
        linkRepository.findById(link.getId()).orElseThrow(
                () -> new IllegalArgumentException("Link with id " + link.getId() + " not found")
        );

        Link linkToUpdate = linkRepository.getOne(link.getId());

        linkToUpdate.setUrl(link.getUrl());
        linkToUpdate.setLastUpdated(link.getLastUpdated());
        linkToUpdate.setLastChecked(link.getLastChecked());

        return linkRepository.save(linkToUpdate);
    }

    @Transactional
    @Override
    public List<Link> findAll() {
        return linkRepository.findAll();
    }
}
