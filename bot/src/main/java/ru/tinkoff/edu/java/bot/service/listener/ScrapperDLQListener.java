package ru.tinkoff.edu.java.bot.service.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;

@Service
@Log4j2
@RabbitListener(queues = "${app.scrapper-queue.name}" + ".dlq")
public class ScrapperDLQListener {
    @RabbitHandler
    public void receiver(LinkUpdateRequest request) {
        log.info("Received failed message: " + request.toString());
    }
}
