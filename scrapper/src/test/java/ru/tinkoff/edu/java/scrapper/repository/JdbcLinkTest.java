package ru.tinkoff.edu.java.scrapper.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Testcontainers
public class JdbcLinkTest extends IntegrationEnvironment {
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void findAll() {
        linkRepository.findAll();
    }
}