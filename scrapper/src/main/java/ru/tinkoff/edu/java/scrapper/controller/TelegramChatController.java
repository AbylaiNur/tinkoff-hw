package ru.tinkoff.edu.java.scrapper.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tg-chat")
public class TelegramChatController {

    @PostMapping("/{id}")
    public void createChat(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable Long id) {

    }
}
