package ru.tinkoff.edu.java.scrapper.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

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
