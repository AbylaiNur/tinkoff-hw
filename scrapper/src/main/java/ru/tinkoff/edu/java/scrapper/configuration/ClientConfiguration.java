package ru.tinkoff.edu.java.scrapper.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    @Value("${base-url.stackoverflow:stackoverflow.com}")
    private String stackoverflowUrl;

    @Value("${base-url.github:github.com}")
    private String githubUrl;

    @Bean("stackoverflowWebClient")
    public WebClient stackoverflowWebClient() {
        return WebClient.builder()
                .baseUrl(stackoverflowUrl)
                .build();
    }

    @Bean
    public WebClient githubWebClient() {
        return WebClient.builder()
                .baseUrl(githubUrl)
                .build();
    }
}
