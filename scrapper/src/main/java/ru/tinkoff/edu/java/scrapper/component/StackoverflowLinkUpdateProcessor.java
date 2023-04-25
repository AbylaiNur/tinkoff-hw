package ru.tinkoff.edu.java.scrapper.component;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkParser.parsers.StackoverflowLinkParser;
import ru.tinkoff.edu.java.scrapper.client.StackoverflowClient;
import ru.tinkoff.edu.java.scrapper.client.TgBotClient;
import ru.tinkoff.edu.java.scrapper.dto.response.GetStackoverflowQuestionDataResponse;
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
public class StackoverflowLinkUpdateProcessor {
    private final StackoverflowClient stackoverflowClient;
    private final TgBotClient tgBotClient;
    private final JdbcLinkRepository linkRepository;
    private final ChatRepository chatRepository;
    private StackoverflowLinkParser stackoverflowLinkParser = new StackoverflowLinkParser();

    public void process(Link link) {
        Map<String, String> parsedLink = stackoverflowLinkParser.parse(link.getUrl().toString());
        String questionId = parsedLink.get("id");
        GetStackoverflowQuestionDataResponse questionData =
                stackoverflowClient.getStackoverflowQuestionData(questionId);
        if (!Objects.equals(questionData.lastActivityDate(), link.getLastUpdated())) {
            link.setLastChecked(OffsetDateTime.now());
            link.setLastUpdated(questionData.lastActivityDate());
            linkRepository.update(link);
            tgBotClient.updates(
                    link.getId(),
                    link.getUrl(),
                    "updated",
                    chatRepository.findAllByLinkId(link.getId()).stream().map(Chat::getId).collect(Collectors.toList()));
        }
    }
}
