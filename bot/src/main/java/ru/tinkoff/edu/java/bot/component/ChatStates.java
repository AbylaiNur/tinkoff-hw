package ru.tinkoff.edu.java.bot.component;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChatStates {
    private Map<Long, String> states = new HashMap<>();
    private Map<Long, String> lastActiveCommands = new HashMap<>();

    public void setState(Long chatId, String state) {
        states.put(chatId, state);
    }

    public void setLastActiveCommand(Long chatId, String command) {
        lastActiveCommands.put(chatId, command);
    }

    public String getState(Long chatId) {
        return states.get(chatId);
    }

    public String getLastActiveCommand(Long chatId) {
        return lastActiveCommands.get(chatId);
    }
}
