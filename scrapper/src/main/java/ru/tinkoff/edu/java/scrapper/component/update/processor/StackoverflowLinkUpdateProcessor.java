package ru.tinkoff.edu.java.scrapper.component.update.processor;


import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkParser.parsers.StackoverflowLinkParser;
import ru.tinkoff.edu.java.scrapper.client.StackoverflowClient;
import ru.tinkoff.edu.java.scrapper.dto.response.GetStackoverflowQuestionDataResponse;
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
public class StackoverflowLinkUpdateProcessor implements LinkUpdateProcessor {
    private final StackoverflowClient stackoverflowClient;
    private final LinkUpdateSender linkUpdateSender;
    private final LinkService linkService;
    private final ChatService chatService;
    private final StackoverflowLinkParser stackoverflowLinkParser;
    private final String host;

    public StackoverflowLinkUpdateProcessor(
        StackoverflowClient stackoverflowClient,
        LinkUpdateSender linkUpdateSender,
        LinkService linkService,
        ChatService chatService
    ) {
        this.stackoverflowClient = stackoverflowClient;
        this.linkUpdateSender = linkUpdateSender;
        this.linkService = linkService;
        this.chatService = chatService;
        this.stackoverflowLinkParser = new StackoverflowLinkParser();
        this.host = "stackoverflow.com";
    }

    public void process(Link link) {
        Map<String, String> parsedLink =
            stackoverflowLinkParser.parse(link.getUrl().toString());
        String questionId = parsedLink.get("id");
        GetStackoverflowQuestionDataResponse questionData =
                stackoverflowClient.getStackoverflowQuestionData(questionId);
        if (!questionData.lastActivityDate().equals(link.getLastUpdated())) {
            link.setLastChecked(OffsetDateTime.now());
            link.setLastUpdated(questionData.lastActivityDate());
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
