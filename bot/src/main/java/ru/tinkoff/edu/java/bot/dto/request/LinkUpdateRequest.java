package ru.tinkoff.edu.java.bot.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record LinkUpdateRequest(
        @NotNull Long id,
        @NotNull String url,
        String description,
        @NotNull List<Long> tgChatIds
) {
}
