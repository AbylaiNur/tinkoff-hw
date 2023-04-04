package ru.tinkoff.edu.java.bot.repository;

import ru.tinkoff.edu.java.bot.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Temporary repository. There is no database.

public class UserRepository {
    private static List<User> users = List.of();

    public static User addUser(String chatId) {
        List<User> newUsers = new ArrayList<>(users);
        User newUser = new User(chatId);
        newUsers.add(newUser);
        users = newUsers;
        return newUser;
    }

    public static User findUserByChatId(String chatId) {
        for (User user : users) {
            if (Objects.equals(chatId, user.getChatId())) {
                return user;
            }
        }
        return null;
    }

    public static void addLink(String chatId, String link) {
        User user = findUserByChatId(chatId);
        List<String> newLinks = new ArrayList<>(user.getLinks());
        newLinks.add(link);
        user.setLinks(newLinks);
    }

    public static void deleteLinkByIndex(String chatId, Integer index) {
        User user = findUserByChatId(chatId);
        List<String> newLinks = new ArrayList<>(user.getLinks());
        newLinks.remove(index);
        user.setLinks(newLinks);
    }

    public static void updateBotState(String chatId, String botState) {
        User user = findUserByChatId(chatId);
        user.setBotState(botState);
    }

    public static void updateBotLastActiveCommand(String chatId, String botLastActiveCommand) {
        User user = findUserByChatId(chatId);
        user.setBotLastActiveCommand(botLastActiveCommand);
    }
}
