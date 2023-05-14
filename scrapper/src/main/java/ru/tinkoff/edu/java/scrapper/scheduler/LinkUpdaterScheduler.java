package ru.tinkoff.edu.java.scrapper.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.component.update.processor.LinkUpdateProcessor;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;
import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class LinkUpdaterScheduler {

    private final LinkService linkService;
    private final List<LinkUpdateProcessor> linkUpdateProcessors;


    @Scheduled(fixedDelayString = "#{@getUpdateInterval}")
    public void update() {
//        Todo: add interval for updating only old links
//        List<Link> links =
//              linkRepository.findAllBeforeTime(
//                  OffsetDateTime.now().minusMinutes(1));
        List<Link> links = linkService.findAll();

        for (Link link : links) {
            URI url = URI.create(link.getUrl());
            System.out.println(url);
            System.out.println(link.getUrl());
            System.out.println(url.getHost());
            for (LinkUpdateProcessor
                    linkUpdateProcessor : linkUpdateProcessors) {

                if (linkUpdateProcessor.canProcess(link)) {
                    linkUpdateProcessor.process(link);
                }
            }
        }
    }
}
