package ru.tinkoff.edu.java.bot.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private Long chatId;
    private List<String> links;
    private String botState;
    private String botLastActiveCommand;

    public User(Long chatId) {
        this.chatId = chatId;
        this.links = List.of();
    }
}
