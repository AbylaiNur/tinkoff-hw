package ru.tinkoff.edu.java.scrapper.dto.response;

import java.time.OffsetDateTime;

public record GetGithubRepositoryDataResponse(
        Long id,
        String name,
        String fullName,
        String description,
        OffsetDateTime updatedAt,
        OffsetDateTime pushedAt
) {
}
