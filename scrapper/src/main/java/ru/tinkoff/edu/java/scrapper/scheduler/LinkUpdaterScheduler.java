package ru.tinkoff.edu.java.scrapper.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.component.update.processor.GithubLinkLinkUpdateProcessor;
import ru.tinkoff.edu.java.scrapper.component.update.processor.StackoverflowLinkLinkUpdateProcessor;
import ru.tinkoff.edu.java.scrapper.component.update.processor.LinkUpdateProcessor;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class LinkUpdaterScheduler {

    private final GithubLinkLinkUpdateProcessor githubLinkUpdateProcessor;
    private final StackoverflowLinkLinkUpdateProcessor stackoverflowLinkUpdateProcessor;
    private final JdbcLinkRepository linkRepository;
    private final List<LinkUpdateProcessor> linkUpdateProcessors;


    @Scheduled(fixedDelayString = "#{@getUpdateInterval}")
    public void update() {
        List<Link> links = linkRepository.findAllBeforeTime(OffsetDateTime.now().minusMinutes(1));
        for (Link link : links) {
            URI url = URI.create(link.getUrl());

            for (LinkUpdateProcessor linkUpdateProcessor : linkUpdateProcessors) {
                if (url.getHost().equals(linkUpdateProcessor.getHost())) {
                    linkUpdateProcessor.process(link);
                }
            }
        }
    }
}
