package ru.tinkoff.edu.java.scrapper.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.tinkoff.edu.java.scrapper.client.dto.GithubApiRepositoryDTO;
import ru.tinkoff.edu.java.scrapper.client.dto.response.GetGithubRepositoryDataResponse;


@Service
@RequiredArgsConstructor
public class GithubClient {
    private final WebClient githubWebClient;

    public GetGithubRepositoryDataResponse getGithubRepositoryData(String username, String repository) {
        try {
            GithubApiRepositoryDTO data = this.githubWebClient
                    .get()
                    .uri("/repos/{username}/{repository}", username, repository)
                    .retrieve().bodyToMono(GithubApiRepositoryDTO.class)
                    .block();

            GetGithubRepositoryDataResponse newData = new GetGithubRepositoryDataResponse(
                    data.id(),
                    data.name(),
                    data.fullName(),
                    data.description(),
                    data.updatedAt(),
                    data.pushedAt()
            );

            return newData;
        } catch (WebClientResponseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
