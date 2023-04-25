package ru.tinkoff.edu.java.scrapper.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkParser.parsers.GlobalLinkParser;
import ru.tinkoff.edu.java.scrapper.client.GithubClient;
import ru.tinkoff.edu.java.scrapper.client.TgBotClient;
import ru.tinkoff.edu.java.scrapper.dto.response.GetGithubRepositoryDataResponse;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GithubLinkUpdateProcessor {
    private final GithubClient githubClient;
    private final TgBotClient tgBotClient;
    private final JdbcLinkRepository linkRepository;
    private final ChatRepository chatRepository;
    private GlobalLinkParser globalLinkParser = new GlobalLinkParser();


    public void process(Link link) {

        Map<String, String> parsedLink = globalLinkParser.parse(link.getUrl().toString());

        String username = parsedLink.get("username");
        String repository = parsedLink.get("repository");

        GetGithubRepositoryDataResponse repositoryData =
                githubClient.getGithubRepositoryData(username, repository);
        if (repositoryData.updatedAt() == null) {
            link.setLastUpdated(repositoryData.updatedAt());
            return;
        }
        System.out.println(repositoryData.updatedAt());
        System.out.println(link.getLastUpdated());
        if (!Objects.equals(repositoryData.updatedAt(), link.getLastUpdated())) {
            link.setLastChecked(OffsetDateTime.now());
            link.setLastUpdated(repositoryData.updatedAt());
            linkRepository.update(link);
            tgBotClient.updates(
                    link.getId(),
                    link.getUrl(),
                    "updated",
                    chatRepository.findAllByLinkId(link.getId()).stream().map(Chat::getId).collect(Collectors.toList()));
        }
    }
}
