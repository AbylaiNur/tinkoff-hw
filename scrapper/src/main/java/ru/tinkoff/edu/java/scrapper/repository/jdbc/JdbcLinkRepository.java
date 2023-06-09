package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.row.LinkRowMapper;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcLinkRepository implements LinkRepository {
    private final JdbcTemplate jdbcTemplate;
    private final LinkRowMapper rowMapper;

    @Override
    public Long add(Link link) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO link (url) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, link.getUrl());
            return ps;
        }, keyHolder);

        return ((Number) keyHolder.getKeys().get("id"))
            .longValue();
    }

    @Override
    public List<Link> findAll() {
        return jdbcTemplate.query("SELECT * FROM link", rowMapper);
    }

    @Override
    public boolean remove(Long id) {
        return jdbcTemplate.update("DELETE FROM link WHERE id = ?", id) == 1;
    }

    @Override
    public boolean removeAll() {
        return jdbcTemplate.update("TRUNCATE TABLE link CASCADE ") == 1;
    }


    @Override
    public Link findByUrl(String uri) {
        List<Link> links = jdbcTemplate.query(
            "SELECT * FROM link WHERE url = ?",
                rowMapper,
                uri.toString()
        );

        if (links.isEmpty()) {
            return null;
        } else {
            return links.get(0);
        }
    }

    @Override
    public List<Link> findAllBeforeTime(OffsetDateTime time) {
        List<Link> links = jdbcTemplate.query(
                "SELECT * FROM link "
                    + "WHERE last_checked < ? OR last_checked IS NULL",
                rowMapper,
                Timestamp.from(time.toInstant())
        );
        return links;
    }

    @Override
    public Long update(Link link) {
        jdbcTemplate.update(
                "UPDATE link "
                    + "SET last_updated = ?, last_checked = ? "
                    + "WHERE id = ?",
                link.getLastUpdated(),
                link.getLastChecked(),
                link.getId()
        );
        return link.getId();
    }
}

