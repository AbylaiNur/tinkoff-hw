package ru.tinkoff.edu.java.scrapper.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.component.update.processor.GithubLinkUpdateProcessor;
import ru.tinkoff.edu.java.scrapper.component.update.processor.StackoverflowLinkUpdateProcessor;
import ru.tinkoff.edu.java.scrapper.component.update.processor.UpdateProcessor;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class LinkUpdaterScheduler {

    private final GithubLinkUpdateProcessor githubLinkUpdateProcessor;
    private final StackoverflowLinkUpdateProcessor stackoverflowLinkUpdateProcessor;
    private final JdbcLinkRepository linkRepository;
    private final List<UpdateProcessor> updateProcessors;


    @Scheduled(fixedDelayString = "#{@getUpdateInterval}")
    public void update() {
        List<Link> links = linkRepository.findAllBeforeTime(OffsetDateTime.now().minusMinutes(1));
        for (Link link : links) {
            URI url = link.getUrl();

            for (UpdateProcessor updateProcessor : updateProcessors) {
                if (url.getHost().equals(updateProcessor.getHost())) {
                    updateProcessor.process(link);
                }
            }
        }
    }
}
