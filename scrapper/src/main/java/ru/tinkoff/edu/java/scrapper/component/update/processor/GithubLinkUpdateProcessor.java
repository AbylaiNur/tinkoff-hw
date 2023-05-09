package ru.tinkoff.edu.java.scrapper.component.update.processor;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkParser.parsers.GithubLinkParser;
import ru.tinkoff.edu.java.scrapper.client.GithubClient;
import ru.tinkoff.edu.java.scrapper.dto.response.GetGithubRepositoryDataResponse;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.sender.LinkUpdateSender;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GithubLinkUpdateProcessor implements LinkUpdateProcessor {
    private final GithubClient githubClient;
    private final LinkUpdateSender linkUpdateSender;
    private final ChatService chatService;
    private final LinkService linkService;
    private final GithubLinkParser githubLinkParser;
    private final String host;

    public GithubLinkUpdateProcessor(
        GithubClient githubClient,
        LinkUpdateSender linkUpdateSender,
        ChatService chatService,
        LinkService linkService
    ) {
        this.githubClient = githubClient;
        this.linkUpdateSender = linkUpdateSender;
        this.chatService = chatService;
        this.linkService = linkService;
        this.githubLinkParser = new GithubLinkParser();
        this.host = "github.com";
    }

    @Override
    public void process(Link link) {

        Map<String, String> parsedLink
            = githubLinkParser.parse(link.getUrl().toString());

        String username = parsedLink.get("username");
        String repository = parsedLink.get("repository");

        GetGithubRepositoryDataResponse repositoryData =
                githubClient.getGithubRepositoryData(username, repository);

        if (repositoryData.updatedAt() == null) {
            link.setLastUpdated(repositoryData.updatedAt());
            return;
        }

        if (!repositoryData.updatedAt().equals(link.getLastUpdated())) {
            link.setLastChecked(OffsetDateTime.now());
            link.setLastUpdated(repositoryData.updatedAt());
            linkService.update(link);
            linkUpdateSender.send(
                    link.getId(),
                    link.getUrl(),
                    "updated",
                    chatService.findAllByLink(link.getUrl())
                        .stream()
                        .map(Chat::getId)
                        .collect(Collectors.toList()));
        }
    }

    @Override
    public boolean canProcess(Link link) {
        URI url = URI.create(link.getUrl());
        return url.getHost().equals(host);
    }
}
