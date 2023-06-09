package ru.tinkoff.edu.java.bot;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.command.Command;
import ru.tinkoff.edu.java.bot.service.UserMessageProcessor;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyBot extends TelegramLongPollingBot {

    private final String telegramBotToken;
    private final UserMessageProcessor userMessageProcessor;
    private final Counter counter;


    public MyBot(String telegramBotToken,
                 UserMessageProcessor userMessageProcessor,
                 MeterRegistry meterRegistry) {
        this.telegramBotToken = telegramBotToken;
        this.userMessageProcessor = userMessageProcessor;
        this.counter = Counter.builder("processed_messages")
            .description("Number of processed messages")
            .register(meterRegistry);
        createCommandMenu();
    }

    public void createCommandMenu() {
        List<BotCommand> listOfCommands = new ArrayList<>();
        for (Command command : userMessageProcessor.getCommands()) {
            listOfCommands.add(
                new BotCommand(command.getCommand(), command.getDescription())
            );
        }

        try {
            this.execute(
                new SetMyCommands(
                    listOfCommands,
                    new BotCommandScopeDefault(),
                    null)
            );
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

    @Override
    public String getBotUsername() {
        return "NotGoodEvilBot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                SendMessage sendMessageResponse =
                    userMessageProcessor.handle(update);
                try {
                    execute(sendMessageResponse);
                    counter.increment();
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void notifyUser(Long tgChatId, String url, String description) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(tgChatId);
        sendMessage.setText(url + "\n" + description);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
