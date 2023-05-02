package ru.tinkoff.edu.java.scrapper.configuration.sender.use;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.service.producer.ScrapperQueueProducer;
import ru.tinkoff.edu.java.scrapper.service.sender.LinkUpdateSender;
import ru.tinkoff.edu.java.scrapper.service.sender.RabbitLinkUpdateSender;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
public class RabbitUseConfiguration {
    @Bean
    public LinkUpdateSender linkUpdateSender(ScrapperQueueProducer scrapperQueueProducer) {
        return new RabbitLinkUpdateSender(scrapperQueueProducer);
    }
}
