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

    public ListLinksResponse listLinks(Long tgChatId) {
        try {
            return this.scrapperClient
                    .get()
                    .uri("/links")
                    .header("Tg-Chat-Id", String.valueOf(tgChatId))
                    .retrieve().bodyToMono(ListLinksResponse.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addLink(Long tgChatId, AddLinkRequest request) {
        this.scrapperClient
                .post()
                .uri("/links")
                .header("Tg-Chat-Id", String.valueOf(tgChatId))
                .bodyValue(request)
                .retrieve().bodyToMono(ListLinksResponse.class)
                .block();
    }

    public void deleteLinks(Long tgChatId, RemoveLinkRequest request) {
        this.scrapperClient
                .method(HttpMethod.DELETE)
                .uri("/links")
                .header("Tg-Chat-Id", String.valueOf(tgChatId))
                .bodyValue(request)
                .retrieve().bodyToMono(RemoveLinkRequest.class)
                .block();
    }

    public void registerTgChat(Long tgChatId) {
        this.scrapperClient
                .post()
                .uri("/tg-chat/{tgChatId}", tgChatId)
                .retrieve().bodyToMono(String.class)
                .block();
    }

    public void deleteTgChat(Long tgChatId) {
        this.scrapperClient
                .delete()
                .uri("/tg-chat/{id}", tgChatId);
    }
}
