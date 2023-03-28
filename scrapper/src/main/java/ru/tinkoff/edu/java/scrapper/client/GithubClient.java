package ru.tinkoff.edu.java.scrapper.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.client.classes.GetGithubRepositoryDataResponse;

import java.util.Map;

@Service
public class GithubClient {
    private final WebClient webClient;

    public GithubClient(@Qualifier("githubWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public GetGithubRepositoryDataResponse getGithubRepositoryData(Map<String, String> map) {
        String username = map.get("username");
        String repository = map.get("repository");
        return this.webClient
                .get()
                .uri("/{username}/{repository}", username, repository)
                .retrieve().bodyToMono(GetGithubRepositoryDataResponse.class)
                .block();
    }
}
