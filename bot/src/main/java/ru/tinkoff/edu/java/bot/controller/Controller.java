package ru.tinkoff.edu.java.bot.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.service.LinkUpdateProcessor;

@RestController
@RequestMapping("/updates")
@RequiredArgsConstructor
public class Controller {

    private final LinkUpdateProcessor linkUpdateProcessor;

    @PostMapping
    public void linkUpdate(@Valid @RequestBody LinkUpdateRequest request) {
        linkUpdateProcessor.process(request);
    }
}
