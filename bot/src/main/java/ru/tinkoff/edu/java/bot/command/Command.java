package ru.tinkoff.edu.java.bot.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@AllArgsConstructor
public abstract class Command {

    private String command;
    private String description;
    public abstract SendMessage handle(Update update);
}
