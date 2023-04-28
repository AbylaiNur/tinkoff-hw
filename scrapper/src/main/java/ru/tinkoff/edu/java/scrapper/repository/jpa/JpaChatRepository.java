package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.edu.java.scrapper.model.Chat;

public interface JpaChatRepository extends JpaRepository<Chat, Long> {
}
