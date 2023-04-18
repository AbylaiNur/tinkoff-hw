package ru.tinkoff.edu.java.scrapper.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.model.Link;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class LinkRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Link> rowMapper;

    public void add(Link link) {
        jdbcTemplate.update(
                "instert into link(url, chatId) values(?, ?) returning *",
                new Object[] { link.getUrl(), link.getChatId() },
                rowMapper
        );
    }

    public List<Link> findAll() {
        return jdbcTemplate.query("select * from link", rowMapper);
    }

    public Boolean remove(Long id) {
        return jdbcTemplate.update("delete from links where id = ?", new Object[] {id}) == 1;
    }
}
