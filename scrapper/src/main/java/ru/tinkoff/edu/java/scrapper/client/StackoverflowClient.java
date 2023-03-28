package ru.tinkoff.edu.java.scrapper.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StackoverflowClient {
    private final WebClient webClient;

    public StackoverflowClient(@Qualifier("stackoverflowWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

}
