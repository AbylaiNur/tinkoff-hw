package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @Value("${scrapperUrl:localhost:8080}")
    private String scrapperUrl;

    @Bean
    public WebClient scrapperWebClient() {
        return WebClient.builder()
                .baseUrl(scrapperUrl)
                .build();
    }
}
