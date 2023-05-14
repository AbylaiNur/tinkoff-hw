package ru.tinkoff.edu.java.bot;


import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@AllArgsConstructor
public class TelegramBotsApiInitializer {

    private final MyBot myBot;

    @PostConstruct
    public void init() {
        try {
            TelegramBotsApi telegramBotsApi =
                new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this.myBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
