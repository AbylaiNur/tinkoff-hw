package ru.tinkoff.edu.java.bot.dto;

import jakarta.validation.constraints.NotNull;

public record ScrapperQueueInfo(
        @NotNull
        String name
) {
}
