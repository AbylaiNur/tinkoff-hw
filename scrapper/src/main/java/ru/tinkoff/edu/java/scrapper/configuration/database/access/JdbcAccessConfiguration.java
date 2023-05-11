package ru.tinkoff.edu.java.scrapper.configuration.database.access;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcChatService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {

    @Bean
    public LinkService linkService(JdbcLinkRepository jdbcLinkRepository,
                                       JdbcChatLinkRepository jdbcChatLinkRepository
    ) {
        return new JdbcLinkService(jdbcLinkRepository, jdbcChatLinkRepository);
    }

    @Bean
    public ChatService chatService(JdbcChatRepository jdbcChatRepository,
                                       JdbcLinkRepository jdbcLinkRepository,
                                       JdbcChatLinkRepository jdbcChatLinkRepository
    ) {
        return new JdbcChatService(jdbcChatRepository, jdbcLinkRepository, jdbcChatLinkRepository);
    }
}
