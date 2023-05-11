package ru.tinkoff.edu.java.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record GithubApiRepositoryDTO(
        Long id,

        String name,

        @JsonProperty("full_name")
        String fullName,

        String description,

        @JsonProperty("updated_at")
        OffsetDateTime updatedAt,

        @JsonProperty("pushed_at")
        OffsetDateTime pushedAt
) {
}
