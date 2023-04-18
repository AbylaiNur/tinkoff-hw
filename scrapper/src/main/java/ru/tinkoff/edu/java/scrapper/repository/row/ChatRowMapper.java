package ru.tinkoff.edu.java.scrapper.repository.row;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.model.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatRowMapper implements RowMapper<Chat> {

    @Override
    public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Chat()
                .setId(rs.getLong("id"))
                .setBotState(rs.getString("botState"))
                .setBotLastActiveCommand(rs.getString("botLastActiveCommand"));
    }
}
