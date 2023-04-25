package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

@Service
@RequiredArgsConstructor
public class JdbcChatService implements ChatService {


    private final JdbcChatRepository chatRepository;

    @Override
    public void register(long tgChatId) {
        chatRepository.add(tgChatId);
    }

    @Override
    public void unregister(long tgChatId) {
        chatRepository.remove(tgChatId);
    }
}
