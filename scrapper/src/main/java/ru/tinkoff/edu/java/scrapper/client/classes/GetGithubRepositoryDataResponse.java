package ru.tinkoff.edu.java.scrapper.client.classes;

import java.time.OffsetDateTime;

public record GetGithubRepositoryDataResponse(
        Long id,
        String name,
        String fullName,
        String html_url,
        String description,
        Boolean fork,
        OffsetDateTime created_at,
        OffsetDateTime updated_at,
        OffsetDateTime pushed_at
) {
}
