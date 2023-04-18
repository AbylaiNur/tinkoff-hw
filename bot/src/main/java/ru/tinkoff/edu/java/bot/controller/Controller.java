package ru.tinkoff.edu.java.bot.controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;

@RestController
@RequestMapping("/updates")
public class Controller {

    @PostMapping
    public void linkUpdate(@Valid @RequestBody LinkUpdateRequest request) {
        System.out.println(request.id());
        System.out.println(request.url());
        System.out.println(request.description());
        for (Long tgChatId : request.tgChatIds()) {
            System.out.println(tgChatId);
        }
    }
}