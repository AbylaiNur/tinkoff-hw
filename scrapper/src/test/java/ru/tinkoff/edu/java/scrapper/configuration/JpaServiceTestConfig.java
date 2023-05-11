package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaChatService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkService;


@TestConfiguration
public class JpaServiceTestConfig {

    @Bean("jpa-link-service-test")
    public JpaLinkService linkService(JpaChatRepository jpaChatRepository,
                                      JpaLinkRepository jpaLinkRepository,
                                      JpaChatLinkRepository jpaChatLinkRepository
    ) {
        return new JpaLinkService(jpaChatRepository, jpaLinkRepository, jpaChatLinkRepository);
    }

    @Bean("jpa-chat-service-test")
    public JpaChatService chatService(JpaChatRepository jpaChatRepository,
                                      JpaLinkRepository jpaLinkRepository,
                                      JpaChatLinkRepository jpaChatLinkRepository
    ) {
        return new JpaChatService(jpaChatRepository, jpaLinkRepository, jpaChatLinkRepository);
    }
}
