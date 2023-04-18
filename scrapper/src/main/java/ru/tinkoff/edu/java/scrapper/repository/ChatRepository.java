package ru.tinkoff.edu.java.scrapper.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.model.Chat;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ChatRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Chat> rowMapper;

    public void add(Chat chat) {
        jdbcTemplate.update(
                "instert into chat(url, chatId) values(?, ?) returning *",
                new Object[] { chat.getBotState(), chat.getBotLastActiveCommand() },
                rowMapper
        );
    }

    public List<Chat> findAll() {
        return jdbcTemplate.query("select * from chats", rowMapper);
    }

    public Boolean remove(Long id) {
        return jdbcTemplate.update("delete from chats where id = ?", new Object[] {id}) == 1;
    }
}
