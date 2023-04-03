package ru.tinkoff.edu.java.scrapper.controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinksController {

    @GetMapping
    public ListLinksResponse listLinks(@RequestHeader("Tg-Chat-Id") Long chatId) {
        return new ListLinksResponse(List.of(new LinkResponse(1l, "link")), 1);
    }

    @PostMapping
    public LinkResponse addLink(@Valid @RequestBody AddLinkRequest request, @RequestHeader("Tg-Chat-Id") Long chatId) {
        return new LinkResponse(chatId, request.link());
    }

    @DeleteMapping LinkResponse removeLink(@RequestBody RemoveLinkRequest request, @RequestHeader("Tg-Chat-Id") Long chatId) {
        return new LinkResponse(chatId, request.link());
    }
}
