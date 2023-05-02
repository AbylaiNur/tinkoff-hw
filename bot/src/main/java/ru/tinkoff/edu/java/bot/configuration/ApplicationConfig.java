package ru.tinkoff.edu.java.bot.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.bot.dto.RabbitmqInfo;
import ru.tinkoff.edu.java.bot.dto.ScrapperQueueInfo;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
        @NotNull String test,
        @NotNull String telegramBotToken,
        @NotNull RabbitmqInfo rabbitmq,
        @NotNull ScrapperQueueInfo scrapperQueue
        ) {
}