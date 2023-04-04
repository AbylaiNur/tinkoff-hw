package ru.tinkoff.edu.java.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    String chatId;
    List<String> links;
    String botState;
    String botLastActiveCommand;

    public User(String chatId) {
        this.chatId = chatId;
        this.links = List.of();
    }
}