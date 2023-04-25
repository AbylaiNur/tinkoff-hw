package ru.tinkoff.edu.java.scrapper.dto.row;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.model.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChatRowMapper implements RowMapper<Chat> {

    @Override
    public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Chat().setId(rs.getLong("id"));
    }
}
