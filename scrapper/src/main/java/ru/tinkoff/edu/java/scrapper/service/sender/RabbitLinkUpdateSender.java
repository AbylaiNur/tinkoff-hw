package ru.tinkoff.edu.java.scrapper.service.sender;

import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.service.producer.ScrapperQueueProducer;

import java.util.List;


@RequiredArgsConstructor
public class RabbitLinkUpdateSender implements LinkUpdateSender {

    private final ScrapperQueueProducer scrapperQueueProducer;

    @Override
    public void send(
        Long id,
        String url,
        String description,
        List<Long> tgChatIds
    ) {
        LinkUpdateRequest linkUpdateRequest
            = new LinkUpdateRequest(id, url, description, tgChatIds);
        scrapperQueueProducer.send(linkUpdateRequest);
    }
}
