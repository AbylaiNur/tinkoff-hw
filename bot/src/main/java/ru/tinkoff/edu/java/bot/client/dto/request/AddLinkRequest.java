package ru.tinkoff.edu.java.bot.client.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
public record AddLinkRequest(
        @NotNull
        URI link
) {
}
