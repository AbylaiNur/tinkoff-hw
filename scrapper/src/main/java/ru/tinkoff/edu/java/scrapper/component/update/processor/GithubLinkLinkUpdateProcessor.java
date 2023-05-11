package ru.tinkoff.edu.java.scrapper.component.update.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkParser.parsers.GithubLinkParser;
import ru.tinkoff.edu.java.scrapper.client.GithubClient;
import ru.tinkoff.edu.java.scrapper.client.TgBotClient;
import ru.tinkoff.edu.java.scrapper.dto.response.GetGithubRepositoryDataResponse;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GithubLinkLinkUpdateProcessor extends LinkUpdateProcessor {
    private final GithubClient githubClient;
    private final TgBotClient tgBotClient;
    private final ChatService chatService;
    private final LinkService linkService;
    private GithubLinkParser githubLinkParser = new GithubLinkParser();
    private String host = "github.com";

    @Override
    public void process(Link link) {

        Map<String, String> parsedLink = githubLinkParser.parse(link.getUrl().toString());

        String username = parsedLink.get("username");
        String repository = parsedLink.get("repository");

        GetGithubRepositoryDataResponse repositoryData =
                githubClient.getGithubRepositoryData(username, repository);

        if (repositoryData.updatedAt() == null) {
            link.setLastUpdated(repositoryData.updatedAt());
            return;
        }

        if (!Objects.equals(repositoryData.updatedAt(), link.getLastUpdated())) {
            link.setLastChecked(OffsetDateTime.now());
            link.setLastUpdated(repositoryData.updatedAt());
            linkService.update(link);
            tgBotClient.updates(
                    link.getId(),
                    link.getUrl(),
                    "updated",
                    chatService.findAllByLink(link.getUrl()).stream().map(Chat::getId).collect(Collectors.toList()));
        }
    }
}
