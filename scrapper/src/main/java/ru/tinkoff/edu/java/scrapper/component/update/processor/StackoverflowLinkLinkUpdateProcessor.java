package ru.tinkoff.edu.java.scrapper.component.update.processor;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkParser.parsers.StackoverflowLinkParser;
import ru.tinkoff.edu.java.scrapper.client.StackoverflowClient;
import ru.tinkoff.edu.java.scrapper.client.TgBotClient;
import ru.tinkoff.edu.java.scrapper.dto.response.GetStackoverflowQuestionDataResponse;
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
public class StackoverflowLinkLinkUpdateProcessor extends LinkUpdateProcessor {
    private final StackoverflowClient stackoverflowClient;
    private final TgBotClient tgBotClient;
    private final LinkService linkService;
    private final ChatService chatService;
    private StackoverflowLinkParser stackoverflowLinkParser = new StackoverflowLinkParser();
    private String host = "stackoverflow.com";

    public void process(Link link) {
        Map<String, String> parsedLink = stackoverflowLinkParser.parse(link.getUrl().toString());
        String questionId = parsedLink.get("id");
        GetStackoverflowQuestionDataResponse questionData =
                stackoverflowClient.getStackoverflowQuestionData(questionId);
        if (!Objects.equals(questionData.lastActivityDate(), link.getLastUpdated())) {
            link.setLastChecked(OffsetDateTime.now());
            link.setLastUpdated(questionData.lastActivityDate());
            linkService.update(link);
            tgBotClient.updates(
                    link.getId(),
                    link.getUrl(),
                    "updated",
                    chatService.findAllByLink(link.getUrl()).stream().map(Chat::getId).collect(Collectors.toList()));
        }
    }
}