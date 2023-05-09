package ru.tinkoff.edu.java.scrapper.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tg-chat")
public class TelegramChatController {

    private final ChatService chatService;
    @PostMapping("/{id}")
    public void createChat(@PathVariable Long id) {
        chatService.register(id);
        System.out.println("registered" + id);
    }

    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable Long id) {
        chatService.unregister(id);
    }
}
