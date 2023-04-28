package ru.tinkoff.edu.java.scrapper.repository;

import ru.tinkoff.edu.java.scrapper.model.Chat;

import java.util.List;

public interface ChatRepository {
    Long add(Long id);
    List<Chat> findAll();
    boolean remove(Long id);
    boolean removeAll();
}
