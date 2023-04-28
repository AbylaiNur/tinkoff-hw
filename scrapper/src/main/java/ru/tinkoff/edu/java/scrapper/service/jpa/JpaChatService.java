package ru.tinkoff.edu.java.scrapper.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

import java.util.List;

@RequiredArgsConstructor
public class JpaChatService implements ChatService {

    private final JpaChatRepository chatRepository;
    private final JpaLinkRepository linkRepository;
    private final JpaChatLinkRepository chatLinkRepository;

    @Transactional
    @Override
    public void register(long tgChatId) {
        chatRepository.save(new Chat().setId(tgChatId));
    }

    @Transactional
    @Override
    public void unregister(long tgChatId) {

        chatRepository.findById(tgChatId).orElseThrow(
                () -> new IllegalArgumentException("Chat with id " + tgChatId + " not found"));

        List<Link> links = chatLinkRepository.findLinksByChatId(tgChatId);

        links.forEach(link -> chatLinkRepository.deleteByChatIdAndLinkId(tgChatId, link.getId()));

        chatRepository.deleteById(tgChatId);
    }

    @Transactional
    @Override
    public List<Chat> findAllByLink(String url) {
        Link link = linkRepository.findByUrl(url).orElseThrow(
                () -> new IllegalArgumentException("Link not found")
        );
        return chatLinkRepository.findChatsByLinkId(link.getId());
    }
}
