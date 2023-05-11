package ru.tinkoff.edu.java.scrapper.client;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdateRequest;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TgBotClient {

    private final WebClient tgBotWebClient;

    public void updates(Long id, String url, String description, List<Long> tgChatIds) {
        this.tgBotWebClient
                .post()
                .uri("/updates")
                .bodyValue(new LinkUpdateRequest(id, url, description, tgChatIds))
                .retrieve().bodyToMono(Void.class)
                .block();
    }
}
