package ru.tinkoff.edu.java.bot;


import jakarta.inject.Inject;
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

import java.util.ArrayList;
import java.util.List;

@Component
public class MyBot extends TelegramLongPollingBot {

    private String telegramBotToken;
    private UserMessageProcessor userMessageProcessor;


    public MyBot(String telegramBotToken, UserMessageProcessor userMessageProcessor) {
        this.telegramBotToken = telegramBotToken;
        this.userMessageProcessor = userMessageProcessor;

        createCommandMenu();
    }

    public void createCommandMenu() {
        List<BotCommand> listOfCommands = new ArrayList<>();
        for (Command command : userMessageProcessor.getCommands()) {
            listOfCommands.add(new BotCommand(command.getCommand(), command.getDescription()));
        }

        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
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
        if (update.hasMessage()){
            Message message = update.getMessage();
            if (message.hasText()){
                SendMessage sendMessageResponse = userMessageProcessor.handle(update);
                try {
                    execute(sendMessageResponse);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
