package ru.tinkoff.edu.java.bot.client.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record AddLinkRequest(
        @NotNull
        String link
) {
}
