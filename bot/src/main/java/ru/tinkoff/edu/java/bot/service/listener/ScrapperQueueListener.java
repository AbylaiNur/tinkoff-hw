package ru.tinkoff.edu.java.bot.service.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.service.LinkUpdateProcessor;

@Service
@RequiredArgsConstructor
@RabbitListener(queues = "${app.scrapper-queue.name}")
public class ScrapperQueueListener {

    private final LinkUpdateProcessor linkUpdateProcessor;
    @RabbitHandler
    public void receiver(LinkUpdateRequest request) {
        linkUpdateProcessor.process(request);
    }
}