package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.row.ChatRowMapper;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcChatRepository implements ChatRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ChatRowMapper rowMapper;

    public Long add(Long id) {
        jdbcTemplate.update("INSERT INTO chat(id) VALUES(?)", id);
        return id;
    }

    public List<Chat> findAll() {
        return jdbcTemplate.query("SELECT * FROM chat", rowMapper);
    }

    public boolean remove(Long id) {
        return jdbcTemplate.update("DELETE FROM chat WHERE id = ?", id) == 1;
    }

    public boolean removeAll() {
        return jdbcTemplate.update("TRUNCATE TABLE chat CASCADE ") == 1;
    }


}
