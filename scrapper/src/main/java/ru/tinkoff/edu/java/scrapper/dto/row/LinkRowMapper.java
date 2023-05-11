package ru.tinkoff.edu.java.scrapper.dto.row;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.model.Link;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component
public class LinkRowMapper implements RowMapper<Link> {

    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Link()
                .setId(rs.getLong("id"))
                .setUrl(rs.getString("url"))
                .setLastChecked(rs.getTimestamp("last_checked").toInstant().atOffset(ZoneOffset.UTC))
                .setLastUpdated(rs.getTimestamp("last_updated").toInstant().atOffset(ZoneOffset.UTC));
    }
}

