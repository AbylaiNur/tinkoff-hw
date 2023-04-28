package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tinkoff.edu.java.scrapper.dto.ChatLinkId;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.ChatLink;
import ru.tinkoff.edu.java.scrapper.model.Link;

import java.util.List;
import java.util.Optional;

public interface JpaChatLinkRepository extends JpaRepository<ChatLink, ChatLinkId> {
    @Query("select cl.link from ChatLink cl where cl.chatId = ?1")
    List<Link> findLinksByChatId(Long chatId);

    @Query("select cl.chat from ChatLink cl where cl.linkId = ?1")
    List<Chat> findChatsByLinkId(Long linkId);

    void deleteByChatIdAndLinkId(Long chatId, Long linkId);

    Optional<ChatLink> findByChatIdAndLinkId(Long chatId, Long linkId);
}
