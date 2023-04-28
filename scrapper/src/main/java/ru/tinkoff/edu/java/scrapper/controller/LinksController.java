package ru.tinkoff.edu.java.scrapper.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/links")
public class LinksController {

    private final LinkService linkService;

    @GetMapping
    public ListLinksResponse listLinks(@RequestHeader("Tg-Chat-Id") Long chatId) {
        List<LinkResponse> links =
                linkService.listAll(chatId)
                .stream()
                .map(link -> new LinkResponse(link.getId(), link.getUrl()))
                .toList();
        ListLinksResponse response = new ListLinksResponse(links, links.size());
        return response;
    }

    @PostMapping
    public LinkResponse addLink(@Valid @RequestBody AddLinkRequest request, @RequestHeader("Tg-Chat-Id") Long chatId) {
        linkService.add(chatId, request.link());
        return new LinkResponse(chatId, request.link());
    }

    @DeleteMapping LinkResponse removeLink(@RequestBody RemoveLinkRequest request, @RequestHeader("Tg-Chat-Id") Long chatId) {
        linkService.remove(chatId, request.link());
        return new LinkResponse(chatId, request.link());
    }
}
