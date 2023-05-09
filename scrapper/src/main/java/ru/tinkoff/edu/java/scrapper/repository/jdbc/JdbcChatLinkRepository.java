package ru.tinkoff.edu.java.scrapper.repository.jdbc;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.row.ChatRowMapper;
import ru.tinkoff.edu.java.scrapper.dto.row.LinkRowMapper;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.ChatLinkRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcChatLinkRepository implements ChatLinkRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ChatRowMapper chatRowMapper;
    private final LinkRowMapper linkRowMapper;


    @Override
    public List<Chat> findChatsByLinkId(Long linkId) {
        return jdbcTemplate.query(
                "SELECT * FROM chat WHERE id IN "
                    + "(SELECT chat_id FROM chat_link WHERE link_id = ?)",
                chatRowMapper,
                linkId
        );
    }

    @Override
    public List<Link> findLinksByChatId(Long chatId) {
        return jdbcTemplate.query(
                "SELECT * FROM link WHERE id IN "
                    + "(SELECT link_id FROM chat_link WHERE chat_id = ?)",
                linkRowMapper,
                chatId
        );
    }

    @Override
    public boolean addLinkToChat(Long linkId, Long chatId) {
        return jdbcTemplate.update(
                "INSERT INTO chat_link (link_id, chat_id) VALUES (?, ?)",
                linkId,
                chatId
        ) == 1;
    }

    @Override
    public boolean removeLinkFromChat(Long linkId, Long chatId) {
        return jdbcTemplate.update(
                "DELETE FROM chat_link WHERE link_id = ? AND chat_id = ?",
                linkId,
                chatId
        ) == 1;
    }
}
