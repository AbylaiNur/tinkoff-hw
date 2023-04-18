package ru.tinkoff.edu.java.scrapper.repository.row;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.model.Link;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkRowMapper implements RowMapper<Link> {

    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Link()
                .setId(rs.getLong("id"))
                .setUrl(rs.getString("url"))
                .setChatId(rs.getLong("chatId"));
    }
}
