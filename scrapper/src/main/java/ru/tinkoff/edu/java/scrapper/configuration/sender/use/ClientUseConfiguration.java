package ru.tinkoff.edu.java.scrapper.configuration.sender.use;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.client.TgBotClient;
import ru.tinkoff.edu.java.scrapper.service.sender.ClientLinkUpdateSender;
import ru.tinkoff.edu.java.scrapper.service.sender.LinkUpdateSender;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "false")
public class ClientUseConfiguration {
    @Bean
    public LinkUpdateSender linkUpdateSender(TgBotClient tgBotClient) {
        return new ClientLinkUpdateSender(tgBotClient);
    }
}
