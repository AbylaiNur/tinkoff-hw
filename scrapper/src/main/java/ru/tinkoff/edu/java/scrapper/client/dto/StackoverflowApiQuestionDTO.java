package ru.tinkoff.edu.java.scrapper.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;

public record StackoverflowApiQuestionDTO(
        List<Item> items
) {
    public static record Item(
            @JsonProperty("is_answered")
            Boolean isAnswered,

            @JsonProperty("view_count")
            Integer viewCount,

            @JsonProperty("answer_count")
            Integer answerCount,

            Integer score,

            String title,

            @JsonProperty("last_activity_date")
            OffsetDateTime lastActivityDate,

            @JsonProperty("last_edit_date")
            OffsetDateTime lastEditDate
    ) {
    }
}
