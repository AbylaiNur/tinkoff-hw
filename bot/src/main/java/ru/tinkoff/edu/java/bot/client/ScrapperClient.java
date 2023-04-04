package ru.tinkoff.edu.java.bot.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.bot.client.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.client.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.client.dto.response.ListLinksResponse;

@Service
@RequiredArgsConstructor
public class ScrapperClient {

    private final WebClient scrapperClient;

    public ListLinksResponse listLinks(Long id) {
        try {
            return this.scrapperClient
                    .get()
                    .uri("/links")
                    .header("Tg-Chat-Id", String.valueOf(id))
                    .retrieve().bodyToMono(ListLinksResponse.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void addLink(Long id, AddLinkRequest request) {
        this.scrapperClient
                .post()
                .uri("/links")
                .header("Tg-Chat-Id", String.valueOf(id))
                .bodyValue(request)
                .retrieve().bodyToMono(ListLinksResponse.class)
                .block();
    }

    public void deleteLinks(Long id, RemoveLinkRequest request) {
        this.scrapperClient
                .method(HttpMethod.DELETE)
                .uri("/links")
                .header("Tg-Chat-Id", String.valueOf(id))
                .bodyValue(request)
                .retrieve().bodyToMono(RemoveLinkRequest.class)
                .block();
    }

    public void getTgChat(Long id) {
        this.scrapperClient
                .post()
                .uri("/tg-chat/{id}", id);
    }

    public void deleteTgChat(Long id) {
        this.scrapperClient
                .delete()
                .uri("/tg-chat/{id}", id);
    }
}
