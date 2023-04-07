package ru.tinkoff.edu.java.bot.dao;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Temporary. There is no database.
@Component
public class UserDao {
    private List<User> users;

    public UserDao() {
        this.users = new ArrayList<>();
    }

    public void addUser(Long chatId) {
        users.add(new User(chatId));
    }

    public User findUserByChatId(Long chatId) {
        for (User user : users) {
            if (Objects.equals(chatId, user.getChatId())) {
                return user;
            }
        }
        return null;
    }

    public void addLink(Long chatId, String link) {
        User user = findUserByChatId(chatId);
        List<String> newLinks = new ArrayList<>(user.getLinks());
        newLinks.add(link);
        user.setLinks(newLinks);
    }

    public void deleteLinkByIndex(Long chatId, Integer index) {
        User user = findUserByChatId(chatId);
        List<String> newLinks = new ArrayList<>(user.getLinks());
        newLinks.remove((int) index);
        user.setLinks(newLinks);
    }

    public void updateBotState(Long chatId, String botState) {
        User user = findUserByChatId(chatId);
        user.setBotState(botState);
    }

    public void updateBotLastActiveCommand(Long chatId, String botLastActiveCommand) {
        User user = findUserByChatId(chatId);
        user.setBotLastActiveCommand(botLastActiveCommand);
    }
}
